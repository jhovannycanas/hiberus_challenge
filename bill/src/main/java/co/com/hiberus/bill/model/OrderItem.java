package co.com.hiberus.bill.model;

import lombok.Data;

import javax.persistence.*;

@Table(schema = "hiberus",name = "tbl_order_item")
@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_caracterizacion", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Transient
    private double subTotal;
}
