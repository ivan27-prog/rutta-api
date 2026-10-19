package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank
        String categoryName
) { }
