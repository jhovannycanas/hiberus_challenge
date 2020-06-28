package co.com.hiberus.logistic.resources;

import co.com.hiberus.logistic.model.Logistic;
import co.com.hiberus.logistic.services.LogisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/rest/api/v1.0/logitics")
public class LogisticResource {

    private final LogisticService logisticService;

    public LogisticResource(LogisticService logisticService) {
        this.logisticService = logisticService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Logistic saveLogistic(@PathVariable("order")Integer orderId, @PathVariable("direction")String direction) {
        return logisticService.saveSentOrder(orderId, direction);
    }
}
