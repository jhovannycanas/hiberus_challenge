package co.com.hiberus.logistic.services;

import co.com.hiberus.logistic.model.Logistic;

public interface LogisticService {

    Logistic saveSentOrder(Integer orderId, String direction);
}
