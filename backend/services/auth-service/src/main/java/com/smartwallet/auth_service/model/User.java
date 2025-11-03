package com.smartwallet.auth_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String username;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
        regexp = "^\\+?\\d{2,3}? ?\\(?\\d{2,3}\\)? ?\\d{4,5}-?\\d{4}$",
        message = "Telefone inválido, use o formato +55 (11) 99999-9999"
    )
    private String phone;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(min = 10, max = 200, message = "O endereço deve ter entre 10 e 200 caracteres")
    private String address;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String password;

    public User() {}

    public User(String username, String password, String email, String phone, String address) {
        this.username  = username;
        this.password  = password;
        this.email     = email;
        this.phone     = phone;
        this.address   = address;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
