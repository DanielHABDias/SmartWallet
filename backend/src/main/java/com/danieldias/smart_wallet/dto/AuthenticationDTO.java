package com.danieldias.smart_wallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "Email is Mandatory")
        @Email(message = "Invalid Email")
        String email,

        @NotBlank(message = "Password is Mandatory")
        String password
) {}