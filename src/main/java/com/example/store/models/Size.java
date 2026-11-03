
package com.example.store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsize")
    private Long id;

    @Column(name = "size_value", nullable = false, length = 20)
    private String sizeValue;

    @ManyToMany(mappedBy = "sizes")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}
