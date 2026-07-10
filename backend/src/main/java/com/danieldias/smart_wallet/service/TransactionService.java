package com.danieldias.smart_wallet.service;

import com.danieldias.smart_wallet.domain.entity.CategoryEntity;
import com.danieldias.smart_wallet.domain.entity.TransactionEntity;
import com.danieldias.smart_wallet.domain.entity.UserEntity;
import com.danieldias.smart_wallet.dto.CreateTransactionDTO;
import com.danieldias.smart_wallet.dto.GetTransactionsDTO;
import com.danieldias.smart_wallet.exception.custom.CategoryNotFoundException;
import com.danieldias.smart_wallet.exception.custom.DatabaseCreateException;
import com.danieldias.smart_wallet.exception.custom.TransactionNotFoundException;
import com.danieldias.smart_wallet.exception.custom.UserNotFoundException;
import com.danieldias.smart_wallet.repository.TransactionRepository;
import com.danieldias.smart_wallet.repository.UserRepository;
import com.danieldias.smart_wallet.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    TransactionRepository transactionRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public List<TransactionEntity> getTransactions(GetTransactionsDTO params){
        List<TransactionEntity> transactionList;
        if(params.transactionId().isPresent()){
            transactionList = this.transactionRepository.findByUserUserIdAndCategoryCategoryId(params.userId(), params.transactionId().get());
        } else {
            transactionList = this.transactionRepository.findByUserUserId(params.userId());
        }
        if(transactionList.isEmpty()){
            throw new TransactionNotFoundException("Transaction not found.");
        }
        return transactionList;
    }

    public TransactionEntity createTransaction(CreateTransactionDTO params){
        UserEntity user = this.userRepository.findById(params.userID()).orElseThrow(UserNotFoundException::new);
        CategoryEntity category = this.categoryRepository.findById(params.categoryID()).orElseThrow(CategoryNotFoundException::new);

        try{
            TransactionEntity transaction = TransactionEntity.builder()
                    .description(params.description())
                    .amount(params.amount())
                    .date(params.date())
                    .type(params.type())
                    .user(user)
                    .category(category)
                    .build();

            return this.transactionRepository.save(transaction);
        } catch (Exception e){
            throw new DatabaseCreateException("Error creating transaction in database");
        }
    }
}
