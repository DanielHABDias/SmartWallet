package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.CategoryEntity;
import com.danieldias.smart_wallet.domain.entity.TransactionEntity;
import com.danieldias.smart_wallet.dto.ApiResponseDTO;
import com.danieldias.smart_wallet.dto.CreateTransactionDTO;
import com.danieldias.smart_wallet.dto.GetTransactionsDTO;
import com.danieldias.smart_wallet.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<List<TransactionEntity>>> getTransactionsUser(@RequestBody @Valid GetTransactionsDTO params) {
        List<TransactionEntity> transactionList = this.transactionService.getTransactions(params);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<List<TransactionEntity>>(true, transactionList.size() + " transactions found!", transactionList));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<TransactionEntity>> createTransaction(@RequestBody @Valid CreateTransactionDTO params){
        TransactionEntity result = this.transactionService.createTransaction(params);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDTO<TransactionEntity>(true, "transaction created!", result));
    }
}
