package co.com.hiberus.chekout.processor;

import co.com.hiberus.chekout.model.Order;
import co.com.hiberus.chekout.model.OrderRequest;
import co.com.hiberus.chekout.services.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class CheckOutProcessor implements Processor {

    private final OrderService orderService;

    public CheckOutProcessor(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        OrderRequest orderRequest = (OrderRequest)exchange.getIn().getBody();
        Order order = orderService.saveOrder(orderRequest);

        exchange.getIn().setHeader("orderid", order.getId());
        exchange.getIn().setHeader("direction", orderRequest.getDirection());
    }
}
