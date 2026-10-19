package com.example.store.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/// @Entity: esta clase representa una tabla de la BD
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /// @Id: llave primaria · IDENTITY: MySQL la genera con AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /// @Column: nombre exacto de la columna en la tabla
    @Column(name = "id_category")
    private Long id;
    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;


    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
