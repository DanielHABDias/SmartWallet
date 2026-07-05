package com.danieldias.smart_wallet.exception.handler;

import com.danieldias.smart_wallet.dto.ApiResponseDTO;
import com.danieldias.smart_wallet.exception.custom.CategoryNotFoundException;
import com.danieldias.smart_wallet.exception.custom.DatabaseCreateException;
import com.danieldias.smart_wallet.exception.custom.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseCreateException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleDatabaseError(DatabaseCreateException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseDTO<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({
            CategoryNotFoundException.class,
            TransactionNotFoundException.class
    })
    public ResponseEntity<ApiResponseDTO<Void>> handleNotFound(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>(false, ex.getMessage(), null));
    }
}