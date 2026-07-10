package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.dto.ApiResponseDTO;
import com.danieldias.smart_wallet.dto.AuthenticationDTO;
import com.danieldias.smart_wallet.dto.LoginResponseDTO;
import com.danieldias.smart_wallet.dto.RegisterDTO;
import com.danieldias.smart_wallet.repository.UserRepository;
import com.danieldias.smart_wallet.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<LoginResponseDTO>> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Object principal = auth.getPrincipal();

        if (!(principal instanceof UserEntity user)) {
            throw new IllegalStateException("Authenticated principal is not a UserEntity.");
        }

        var token = this.tokenService.generateToken(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<LoginResponseDTO>(true, "Login was a succeed!", new LoginResponseDTO(token)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<String>> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLoginOrEmail(data.login(), data.email()) != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDTO<>(false, "Login or Email is already taken!", null));
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity user = new UserEntity(data.login(), encryptedPassword, data.email());

        try{
            this.userRepository.save(user);
        } catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDTO<>(false, "Error to save user!", null));
        }


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<>(true, "Register was a succeed!", null));
    }
}
