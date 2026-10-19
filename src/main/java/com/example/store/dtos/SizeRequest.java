package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SizeRequest(
        @NotBlank(message = "El valor de la talla no puede estar en blanco")
        @Size(min = 1, max = 20, message = "La talla debe tener entre 1 y 20 caracteres")
        String sizeValue
) {
}