package co.com.hiberus.chekout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Integer orderId;
    private String orderDate;
    private Integer billId;
    private Integer logisticId;
    private double totalCost;

}
