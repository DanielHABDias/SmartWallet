package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.dto.ApiResponseDTO;
import com.danieldias.smart_wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<UserEntity>> getUser(@RequestBody Long userId){
        UserEntity user = this.userService.getUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<UserEntity>(true, "User found.", user));
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<UserEntity>>> getUsers(){
        List<UserEntity> users = this.userService.getUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<List<UserEntity>>(true, "User found", users));
    }
}
