package co.com.hiberus.chekout.processor;

import co.com.hiberus.chekout.model.Order;
import co.com.hiberus.chekout.model.OrderResponse;
import co.com.hiberus.chekout.repositories.OrderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionProcessor implements Processor {

    private final OrderRepository orderRepository;

    public TransactionProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper maper = new ObjectMapper();
        String  result = exchange.getIn().getBody(String.class);
        String [] resultSplit = result.split("--");

        JsonNode jsonNodeBill = null;
        JsonNode jsonNodeLogistic = null;
        if (resultSplit[0].contains("clientId")) {
            jsonNodeBill = maper.readTree(resultSplit[0]);
            jsonNodeLogistic = maper.readTree(resultSplit[1]);
        }
        else {
            jsonNodeBill = maper.readTree(resultSplit[1]);
            jsonNodeLogistic = maper.readTree(resultSplit[0]);
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTotalCost(jsonNodeBill.get("totalBill").doubleValue());
        orderResponse.setBillId(jsonNodeBill.get("id").intValue());
        orderResponse.setOrderDate(jsonNodeLogistic.get("createAt").textValue());
        orderResponse.setLogisticId(jsonNodeLogistic.get("id").intValue());

        JsonNode jsonNodeOrder = jsonNodeBill.get("order");
        orderResponse.setOrderId(jsonNodeOrder.get("id").intValue());

        Order order = orderRepository.findById(jsonNodeOrder.get("id").intValue()).get();
        order.setStatus(Order.status.sent.name());
        orderRepository.save(order);

        exchange.getIn().setBody(orderResponse);
    }
}
