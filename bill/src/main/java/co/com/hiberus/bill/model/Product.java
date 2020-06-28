package co.com.hiberus.bill.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(schema = "hiberus",name = "tbl_product")
@Entity
@Data
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
/*
    @Column(name = "name_product")
    private String nameProduct;
    */


    @Column(name = "unit_cost")
    private double unitCost;

}
