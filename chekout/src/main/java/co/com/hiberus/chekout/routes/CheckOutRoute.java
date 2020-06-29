package co.com.hiberus.chekout.routes;

import co.com.hiberus.chekout.exceptions.BadRequestException;
import co.com.hiberus.chekout.model.Order;
import co.com.hiberus.chekout.model.OrderRequest;
import co.com.hiberus.chekout.model.OrderResponse;
import co.com.hiberus.chekout.processor.CheckOutProcessor;
import co.com.hiberus.chekout.processor.TransactionProcessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckOutRoute extends RouteBuilder {

    @Autowired
    private CheckOutProcessor checkOutProcessor;

    @Autowired
    private TransactionProcessor transactionProcessor;

    @Override
    public void configure() throws Exception {

                onException(BadRequestException.class)
                .handled(true).transform()
                .simple("${exception.message}")
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"));

                restConfiguration()
                .bindingMode(RestBindingMode.json)
                .apiContextPath("/api")
                .contextPath("/rest")
                .apiProperty("host", "")
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)

                .apiProperty("api.title", "API Checkout")
                .apiProperty("api.contact.name", "Jhovanny Canas").apiProperty("api.version", "0.0.1");

        rest().description("Checkout Service")
                .consumes("application/json")
                .produces("application/json")

                .post("/checkouts")
                .description("allows you to create an order")
                .type(OrderRequest.class)
                .responseMessage().code(200).message("OK").endResponseMessage()
                .route()
                .streamCaching()
                .to("bean-validator:validateOrder")
                .bean(this.checkOutProcessor)
                .setBody(constant(null))
                .multicast(new AggregationStrategy() {
                    @Override
                    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                        if (oldExchange == null) {
                            return newExchange;
                        } else {
                            String body1 = oldExchange.getIn().getBody(String.class);
                            String body2 = newExchange.getIn().getBody(String.class);
                            String merged =  body1 + "--" + body2;
                            oldExchange.getIn().setBody(merged);
                            return oldExchange;
                        }
                    }
                }).parallelProcessing()
                    .to("direct:billorder", "direct:logisticorder")
                .end()
                .to("direct:result");

        from("direct:billorder")
                .toD("http4:{{bill.service}}/rest/api/v1.0/bills/${header.orderid}?bridgeEndpoint=true&throwExceptionOnFailure=false")
                .setBody(simple("${body}"));

        from("direct:logisticorder")
                .toD("http4:{{logistic.service}}/rest/api/v1.0/logistics/${header.orderid}/${header.direction}?bridgeEndpoint=true&throwExceptionOnFailure=false")
                .setBody(simple("${body}"));

        from("direct:result").bean(transactionProcessor);
    }
}
