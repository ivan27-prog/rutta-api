
package com.example.store.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderRequest(
        LocalDate orderDate,
        String orderStatus,
        BigDecimal orderAmount
) {
}
