package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ColorRequest(
        @NotBlank(message = "El nombre del color no puede estar en blanco")
        @Size(min = 2, max = 45, message = "El color debe tener entre 2 y 45 caracteres")
        String colorName
) {
}