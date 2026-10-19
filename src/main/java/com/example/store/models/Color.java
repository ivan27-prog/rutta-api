package com.example.store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcolor")
    private Long id;

    @Column(name = "color_name", length = 45, nullable = false)
    private String colorName;

    @ManyToMany(mappedBy = "colors")
    @JsonIgnore
    private List<Product> products;
}