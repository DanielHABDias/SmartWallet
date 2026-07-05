package com.danieldias.smart_wallet.service;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.exception.custom.UserNotFoundException;
import com.danieldias.smart_wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserEntity getUser(Long id){
        return this.repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<UserEntity> getUsers(){
        List<UserEntity> userList = this.repository.findAll();
        if(userList.isEmpty()){
            throw new UserNotFoundException("No users found.");
        }
        return userList;
    }
}
