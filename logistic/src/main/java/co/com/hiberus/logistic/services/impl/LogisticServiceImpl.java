package co.com.hiberus.logistic.services.impl;

import co.com.hiberus.logistic.model.Logistic;
import co.com.hiberus.logistic.repositories.LogisticRepository;
import co.com.hiberus.logistic.services.LogisticService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogisticServiceImpl implements LogisticService {

    private final LogisticRepository logisticRepository;

    public LogisticServiceImpl(LogisticRepository logisticRepository) {
        this.logisticRepository = logisticRepository;
    }

    @Override
    public synchronized Logistic saveSentOrder(Integer orderId, String direction) {

        return logisticRepository.save(Logistic.builder().orderId(orderId).direction(direction).createAt(new Date()).build());
    }
}
