package com.danieldias.smart_wallet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorized -> authorized
                        .requestMatchers(HttpMethod.GET, "/category").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .build();
    }
}
