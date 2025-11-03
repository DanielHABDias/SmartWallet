package com.smartwallet.auth_service.controller;

import com.smartwallet.auth_service.dto.UserDTO;
import com.smartwallet.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        String token = userService.register(userDTO);
        return ResponseEntity.ok().body("Token: " + token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO userDTO) {
        String token = userService.login(userDTO);
        return ResponseEntity.ok().body("Token: " + token);
    }
}
