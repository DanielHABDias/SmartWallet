package com.danieldias.smart_wallet.configuration;

import com.danieldias.smart_wallet.component.SecurityFilterComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations  {

    @Autowired
    SecurityFilterComponent securityFilterComponent;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Auth
                        .requestMatchers(HttpMethod.GET, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        // Category
                        .requestMatchers(HttpMethod.GET, "/category/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/category/**").hasRole("ADMIN")
                        // User
                        .requestMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                        // Transaction
                        .requestMatchers(HttpMethod.GET, "/transaction/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/transaction/**").hasRole("USER")
                        // AnyRequest
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilterComponent, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
