package co.com.hiberus.chekout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private int clientId;
    private Date date;
    private String direction;
    private List<ProductRequest> productRequests;
}
