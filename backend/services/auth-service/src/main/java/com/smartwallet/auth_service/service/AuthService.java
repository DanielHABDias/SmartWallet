package com.smartwallet.auth_service.service;

import com.smartwallet.auth_service.dto.UserDTO;
import com.smartwallet.auth_service.model.User;
import com.smartwallet.auth_service.repository.UserRepository;
import com.smartwallet.auth_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(UserDTO dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (user.isEmpty() || !passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }
        return jwtUtil.generateToken(user.get().getEmail());
    }
}
