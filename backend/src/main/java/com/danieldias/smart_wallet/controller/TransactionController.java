package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.dto.RequestCreateTransactionUserDTO;
import com.danieldias.smart_wallet.dto.RequestGetTransactionsUserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/transaction")
public class TransactionController {

    @PostMapping()
    public String getTransactionsUser(RequestGetTransactionsUserDTO params) {
        return "Got: " + params.userId();
    }

    @PostMapping("/create")
    public String createTransactionUser(RequestCreateTransactionUserDTO params){
        return "Created: " + params.userID();
    }
}
