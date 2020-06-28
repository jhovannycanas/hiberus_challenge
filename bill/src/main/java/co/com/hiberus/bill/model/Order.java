package co.com.hiberus.bill.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(schema = "hiberus",name = "tbl_order")
@Entity
@Data
@Builder
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

    @ManyToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public void addItem(OrderItem orderItem) {
        if (orderItems == null){
            orderItems = new ArrayList<>();
        }
        orderItem.setOrder(this);
    }
}
