package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SizeRequest(
        @NotBlank(message = "La talla no puede estar vacía")
        @Size(max = 20, message = "La talla no puede tener más de 20 caracteres")
        String sizeValue
) {
}
