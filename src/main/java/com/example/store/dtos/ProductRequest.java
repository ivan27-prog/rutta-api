package com.example.store.dtos;

import java.math.BigDecimal;

public record ProductRequest(
        String productName,
        BigDecimal price,
        String activity,
        String level

) {
}
