package com.example.store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "description")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_description")
    private Long id;

    @Column(name = "description", length = 200, nullable = false)
    private String descriptionText;

    @OneToOne
    @JoinColumn(name = "productId", nullable = false, unique = true)
    @JsonIgnore
    private Product product;

}
