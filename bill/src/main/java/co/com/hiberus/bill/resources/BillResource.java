package co.com.hiberus.bill.resources;

import co.com.hiberus.bill.model.Bill;
import co.com.hiberus.bill.model.Order;
import co.com.hiberus.bill.services.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = "/rest/api/v1.0/bills")
public class BillResource {

    private final BillService billService;

    public BillResource(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Bill createBill(@PathVariable("order") Integer orderId) {
        return billService.saveService(orderId);
    }
}
