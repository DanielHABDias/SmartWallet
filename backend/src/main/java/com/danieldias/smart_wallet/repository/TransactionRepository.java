package com.danieldias.smart_wallet.repository;

import com.danieldias.smart_wallet.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUserId(Long userId);
    List<TransactionEntity> findByUserIdAndCategoryId(Long userId, Long categoryId);
}
