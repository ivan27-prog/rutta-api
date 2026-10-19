package com.example.store.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;
    /*@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_category",                                    // tabla intermedia
            joinColumns = @JoinColumn(name = "product_id"),               // FK hacia product
            inverseJoinColumns = @JoinColumn(name = "category_id")        // FK hacia category
    )
    private Set<Category> categories = new HashSet<>();*/



}
