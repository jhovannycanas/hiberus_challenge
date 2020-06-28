package co.com.hiberus.chekout.services;

import co.com.hiberus.chekout.model.Order;
import co.com.hiberus.chekout.model.OrderRequest;
import co.com.hiberus.chekout.model.OrderResponse;

public interface OrderService {

    Order saveOrder(OrderRequest orderRequest);
}
