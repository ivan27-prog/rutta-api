package com.example.store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/// @Entity: esta clase representa una tabla de la BD
@Entity
/// @Table: nombre exacto de la tabla en MySQL
//@Table(name = "category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {

    /// @Id: llave primaria · IDENTITY: MySQL la genera con AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /// @Column: nombre exacto de la columna en la tabla
    @Column(name = "id_category")
    private Long id;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")   // nombre del CAMPO en Product, no de la columna
    @JsonIgnore
    private Set<Product> products = new HashSet<>();



}
