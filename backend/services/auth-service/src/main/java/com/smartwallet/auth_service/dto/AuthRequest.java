package com.smartwallet.auth_service.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private String phone;
    private String address;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
