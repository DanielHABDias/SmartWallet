package com.danieldias.smart_wallet.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        String email
) {}
