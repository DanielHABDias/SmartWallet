package com.danieldias.smart_wallet.repository;

import com.danieldias.smart_wallet.domain.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
