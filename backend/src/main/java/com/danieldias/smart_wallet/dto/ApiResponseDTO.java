package com.danieldias.smart_wallet.dto;

public record ApiResponseDTO<T>(
        boolean success,
        String message,
        T data
) {}
