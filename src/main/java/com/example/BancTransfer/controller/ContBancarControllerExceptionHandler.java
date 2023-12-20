package com.example.BancTransfer.controller;

import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.exception.ContBancarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ContBancarControllerExceptionHandler {

    @ExceptionHandler(ContBancarNotFoundException.class)
    public ResponseEntity<Object> contBancarNotFound(ContBancarNotFoundException contBancarNotFoundException) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", contBancarNotFoundException.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BalanceNegativException.class)
    public ResponseEntity<Object> balanceNegativeException(BalanceNegativException balanceNegativException) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", balanceNegativException.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


}
