package com.danieldias.smart_wallet.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity()
@Table(name="users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "userId", callSuper = false)
public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String googleId;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public @NonNull String getUsername() {
        return login;
    }
}
