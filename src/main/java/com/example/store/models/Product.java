package com.example.store.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200, name = "product_name")
    private String productName;

    @Column(nullable = false, name = "product_price")
    private BigDecimal price;

    @Column(name = "activity_type")
    private String activity;

    @Column(name = "level")
    private String level;

    //@ManyToOne
    //@JoinColumn(name = "category_id", nullable = false)
    //@JsonIgnore
    //private Category category;



}
