package co.com.hiberus.chekout.services.impl;

import co.com.hiberus.chekout.model.Order;
import co.com.hiberus.chekout.model.OrderItem;
import co.com.hiberus.chekout.model.OrderRequest;
import co.com.hiberus.chekout.repositories.OrderRepository;
import co.com.hiberus.chekout.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(OrderRequest orderRequest) {

        List<OrderItem> orderItems = orderRequest.getProductRequests().parallelStream()
                .map(productRequest -> OrderItem.builder().product(productRequest.getId())
                .quantity(productRequest.getQuantity()).cost(productRequest.getCost()).build()).collect(Collectors.toList());

        Order order = Order.builder().dateOrder(orderRequest.getDate()).clientId(orderRequest.getClientId())
                .status(Order.status.pending.name()).createAt(new Date()).build();

        if (!orderItems.isEmpty()) {
            for (OrderItem orderItem:
            orderItems) {
                order.addItem(orderItem);
            }
        }

        return orderRepository.save(order);
    }
}
