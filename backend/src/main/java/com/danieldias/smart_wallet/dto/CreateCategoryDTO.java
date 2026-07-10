package com.danieldias.smart_wallet.dto;

import com.danieldias.smart_wallet.domain.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryDTO(
        @NotBlank
        String name,
        @NotBlank
        String color,
        @NotBlank
        String unicodeIcon,
        @NotBlank
        TransactionType transactionType,
        @NotBlank
        @Size(max = 100)
        String description
){}
