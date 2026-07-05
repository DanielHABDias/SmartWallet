package com.danieldias.smart_wallet.dto;

import java.util.Optional;

public record GetTransactionsDTO(Long userId, Optional<Long> transactionId) {}
