
package com.example.store.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_amount", precision = 10, scale = 2)
    private BigDecimal orderAmount;
   // @OneToMany(mappedBy = "order")
    //private List<OrderDetails> orderDetails = new ArrayList<>();
}
