package com.danieldias.smart_wallet.service;

import com.danieldias.smart_wallet.domain.entity.TransactionEntity;
import com.danieldias.smart_wallet.exception.custom.TransactionNotFoundException;
import com.danieldias.smart_wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository repository;

    public TransactionEntity getTransactions(Long id){
        return this.repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found."));
    }
}
