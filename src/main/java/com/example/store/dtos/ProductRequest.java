
package com.example.store.dtos;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String productName,
        BigDecimal price,
        String activity,
        String level,
        @NotNull(message = "La categoría es obligatoria")
        Long categoryId,
        List<Long> colorIds,
        List<Long> sizeIds
) {
}
