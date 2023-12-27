package com.example.BancTransfer.controller.exersari;

import org.springframework.stereotype.Service;

@Service
public class ServiceSum {

    public Integer sum(){
        Integer result = adunare(2,2);
        return result;

    }

    public Integer adunare(Integer a, Integer b){

        return  a + b;

    }
}
