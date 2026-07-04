package com.danieldias.smart_wallet.domain;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false, unique = true)
    private String color;

    @Column(nullable = false, unique = true)
    private String unicodeIcon;
}
