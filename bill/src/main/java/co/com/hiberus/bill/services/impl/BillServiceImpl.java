package co.com.hiberus.bill.services.impl;

import co.com.hiberus.bill.exceptions.OrderNotFound;
import co.com.hiberus.bill.model.Bill;
import co.com.hiberus.bill.model.Order;
import co.com.hiberus.bill.repositories.BillRepository;
import co.com.hiberus.bill.repositories.OrderRepository;
import co.com.hiberus.bill.services.BillService;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final OrderRepository orderRepository;

    public BillServiceImpl(BillRepository billRepository, OrderRepository orderRepository) {
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Bill saveService(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFound("Order not found", 404));

        double totalOrder = order.getOrderItems().stream().map(orderItem -> orderItem.getSubTotal())
                .mapToDouble(value -> value).sum();
        return billRepository.save(Bill.builder().order(order).totalBill(totalOrder).createAt(new Date()).build());
    }
}
