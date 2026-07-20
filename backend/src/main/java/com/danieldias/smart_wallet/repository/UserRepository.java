package com.danieldias.smart_wallet.repository;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
    UserEntity findByEmail(String email);
    UserEntity findByLoginOrEmail(String login, String email);
}
