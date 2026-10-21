package com.example.store.dtos;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductRequest(
        String productName,
        BigDecimal price,
        String activity,
        String level,
        @NotNull(message = "La categoría es obligatoria")
        Long categoryId

) {
}
