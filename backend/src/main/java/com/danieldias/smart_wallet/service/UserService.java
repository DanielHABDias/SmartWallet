package com.danieldias.smart_wallet.service;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserEntity getUser(Long id){
        return this.repository.getReferenceById(id);
    }
}
