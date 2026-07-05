package com.danieldias.smart_wallet.repository;

import com.danieldias.smart_wallet.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
