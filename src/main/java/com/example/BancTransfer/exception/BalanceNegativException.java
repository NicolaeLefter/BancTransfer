package com.example.BancTransfer.exception;

public class BalanceNegativException extends Exception{

    public BalanceNegativException(String message){
        super(message);
    }
}
