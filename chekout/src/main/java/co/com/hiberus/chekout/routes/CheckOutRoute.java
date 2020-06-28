package co.com.hiberus.chekout.routes;

import co.com.hiberus.chekout.model.OrderRequest;
import co.com.hiberus.chekout.processor.CheckOutProcessor;
import co.com.hiberus.chekout.services.OrderService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckOutRoute extends RouteBuilder {


    @Autowired
    private CheckOutProcessor checkOutProcessor;

    @Override
    public void configure() throws Exception {

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
                .route().streamCaching().bean(this.checkOutProcessor)
                .multicast().parallelProcessing()
                    .to("direct:billorder")
                    .to("direct:logisticorder")
                .end()
                .endRest();

        from("direct:billorder")
                .toD("http4://{{bill.service}}/rest/api/v1.0/bills/${header.orderid}?bridgeEndpoint=true&throwExceptionOnFailure=false");


        from("direct:logisticorder")
                .toD("http4://{{bill.service}}/rest/api/v1.0/logitics/${header.orderid}/${header.direction}?bridgeEndpoint=true&throwExceptionOnFailure=false");

    }
}
