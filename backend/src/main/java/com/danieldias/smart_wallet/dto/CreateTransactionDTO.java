package com.danieldias.smart_wallet.dto;

import com.danieldias.smart_wallet.domain.entity.CategoryEntity;
import com.danieldias.smart_wallet.domain.entity.TransactionType;
import com.danieldias.smart_wallet.domain.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

public record CreateTransactionDTO(
        Float amount,
        TransactionType type,
        LocalDate date,
        Long userID,
        Long categoryID,
        String description
) {}