package com.wea4saken.rikmasters.exception.handlers;

import com.wea4saken.rikmasters.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundControllerAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> notFoundProduct(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}