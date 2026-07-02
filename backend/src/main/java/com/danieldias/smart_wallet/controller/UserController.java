package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getLogin(){
        return this.userService.getLogin();
    }
}
