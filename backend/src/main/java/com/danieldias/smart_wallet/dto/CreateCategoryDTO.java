package com.danieldias.smart_wallet.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank
        String description,
        @NotBlank
        String color,
        @NotBlank
        String unicodeIcon
){}
