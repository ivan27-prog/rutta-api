package com.example.store.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderDetailsRequest(
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        Integer quantity,

        @NotNull(message = "El precio unitario es obligatorio")
        @Positive(message = "El precio unitario debe ser mayor a cero")
        BigDecimal unitPrice,

        @NotNull(message = "El id del pedido es obligatorio")
        Long orderId,

        @NotNull(message = "El id del producto es obligatorio")
        Long productId
) {
}