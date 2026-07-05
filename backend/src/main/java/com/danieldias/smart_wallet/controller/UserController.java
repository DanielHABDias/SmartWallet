package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public UserEntity getLogin(@PathVariable Long userId){
        return this.userService.getUser(userId);
    }
}
