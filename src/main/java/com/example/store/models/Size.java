package com.example.store.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
