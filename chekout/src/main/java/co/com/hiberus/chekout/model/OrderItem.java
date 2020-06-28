package co.com.hiberus.chekout.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Table(schema = "hiberus",name = "tbl_order_item")
@Entity
@Data
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;

    @Column(name = "product_id")
    private Integer product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost")
    private double cost;

    @Transient
    private double subTotal;
}
