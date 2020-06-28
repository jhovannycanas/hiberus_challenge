package co.com.hiberus.logistic.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(schema = "hiberus",name = "tbl_logistic")
@Entity
@Data
@Builder
public class Logistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "direction")
    private String direction;

    @Column(name = "orderId")
    private Integer orderId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;
}
