package com.danieldias.smart_wallet.dto;

import com.danieldias.smart_wallet.domain.entity.UserRole;

public record LoginResponseDTO(
        String login,
        String email,
        UserRole role
) {}
