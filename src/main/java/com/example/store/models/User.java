package com.example.store.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// @Table: "user" no es palabra reservada en MySQL, pero se declara explícito

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // @Id: llave primaria · IDENTITY: MySQL la genera con AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column: nombre exacto de la columna en la tabla
    @Column(name = "idusers")
    private Integer id;

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    // unique = true: la tabla tiene UNIQUE INDEX en email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    // En la Tarea 13 se guardará con BCrypt
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    // String (no número): conserva ceros iniciales; VARCHAR(10) en el script
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;
}
