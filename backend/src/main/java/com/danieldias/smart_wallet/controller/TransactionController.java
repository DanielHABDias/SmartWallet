package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.TransactionEntity;
import com.danieldias.smart_wallet.dto.CreateTransactionDTO;
import com.danieldias.smart_wallet.dto.GetTransactionsDTO;
import com.danieldias.smart_wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping()
    public TransactionEntity getTransactionsUser(GetTransactionsDTO params) {
        return this.transactionService.getTransactions(params.userId());
    }

    @PostMapping("/create")
    public String createTransactionUser(CreateTransactionDTO params){
        return "Created: " + params.userID();
    }
}
