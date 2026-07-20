package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.dto.*;
import com.danieldias.smart_wallet.repository.UserRepository;
import com.danieldias.smart_wallet.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<LoginResponseDTO>> login(
            @RequestBody @Valid AuthenticationDTO data,
            HttpServletResponse response
    ){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Object principal = auth.getPrincipal();

        if (!(principal instanceof UserEntity user)) {
            throw new IllegalStateException("Authenticated principal is not a UserEntity.");
        }

        var token = this.tokenService.generateToken(user);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 1 dia
        response.addCookie(cookie);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<LoginResponseDTO>(true, "Login was a succeed!", new LoginResponseDTO(user.getLogin(), user.getEmail(), user.getRole())));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<String>> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLoginOrEmail(data.login(), data.email()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ApiResponseDTO<>(false, "Login or Email is already taken!", null));
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        UserEntity user = new UserEntity(data.login(), encryptedPassword, data.email());

        try{
            this.userRepository.save(user);
        } catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDTO<>(false, "Error to save user!", null));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDTO<>(true, "Register was a succeed!", null));
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponseDTO<?>> validate(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        UserEntity user = null;

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if(cookies != null && cookies.length != 0 && token != null){
            var subject = this.tokenService.validateToken(token);
            user = this.userRepository.findByEmail(subject);
        }

        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDTO<>(false, "Unauthorized", false));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<>(true, "Login is valid!", new LoginResponseDTO(user.getLogin(), user.getEmail(), user.getRole())));
    }
}
