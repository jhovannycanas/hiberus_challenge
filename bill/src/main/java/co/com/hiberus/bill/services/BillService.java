package co.com.hiberus.bill.services;

import co.com.hiberus.bill.model.Bill;
import co.com.hiberus.bill.model.Order;

public interface BillService {
    Bill saveService(Integer orderId);
}
