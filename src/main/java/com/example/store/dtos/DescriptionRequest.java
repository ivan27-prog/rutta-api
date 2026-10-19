package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DescriptionRequest(
        @NotBlank(message = "La descripción no puede estar en blanco")
        @Size(min = 2, max = 200, message = "La descripción debe tener entre 2 y 200 caracteres")
        String descriptionText,

        @NotNull(message = "El id del producto es obligatorio")
        Long productId
) {
}