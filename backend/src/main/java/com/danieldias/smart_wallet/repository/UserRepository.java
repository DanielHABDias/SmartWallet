package com.danieldias.smart_wallet.repository;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
