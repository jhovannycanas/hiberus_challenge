package co.com.hiberus.bill.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "tbl_order")
@Entity
@Data
@NoArgsConstructor
public class Order {
    public enum status {
        pending, sent, cancel
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private Integer clientId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public void addItem(OrderItem orderItem) {
        if (orderItems == null){
            orderItems = new ArrayList<>();
        }
        orderItem.setOrder(this);
    }
}
