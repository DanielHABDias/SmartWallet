package com.danieldias.smart_wallet.exception.handler;

import com.danieldias.smart_wallet.dto.ApiResponseDTO;
import com.danieldias.smart_wallet.exception.custom.CategoryNotFoundException;
import com.danieldias.smart_wallet.exception.custom.DatabaseCreateException;
import com.danieldias.smart_wallet.exception.custom.TransactionNotFoundException;
import com.danieldias.smart_wallet.exception.custom.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            TransactionNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ApiResponseDTO<?>> handleNotFound(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<>(true, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleBadCredentials() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseDTO<>(
                        false,
                        "Not authorized",
                        null
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    return Map.of(
                            "field", error.getField(),
                            "message", Objects.requireNonNull(error.getDefaultMessage())
                    );
                })
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDTO<>(
                        false,
                        "Invalid Params",
                        errors
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleInvalidJson(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponseDTO<>(
                        false,
                        "Invalid JSON",
                        null
                ));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleNotFound(NoResourceFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>(
                        false,
                        "Resource Not Found",
                        null
                ));
    }
}