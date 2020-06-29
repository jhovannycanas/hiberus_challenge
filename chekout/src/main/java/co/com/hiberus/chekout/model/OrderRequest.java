package co.com.hiberus.chekout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private int clientId;
    @NotNull
    private Date date;
    @NotNull
    private String direction;
    @Size(min = 1)
    private List<ProductRequest> productRequests;
}
