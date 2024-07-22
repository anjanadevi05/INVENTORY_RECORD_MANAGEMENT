package com.records.Inventory.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.records.Inventory.response.CommanResponse;
import com.records.Inventory.exception.InventoryException;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = { InventoryException.class })
    public ResponseEntity<CommanResponse<Integer>> inventoryExceptionHandler(InventoryException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommanResponse.<Integer>builder()
                .isError(true)
                .data(null)
                .message(e.getMessage())
                .build());
    }
}
