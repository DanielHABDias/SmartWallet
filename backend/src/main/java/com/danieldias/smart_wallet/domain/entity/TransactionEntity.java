package com.danieldias.smart_wallet.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;

    @Column(nullable = false)
    private float numeric;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryId;
}
