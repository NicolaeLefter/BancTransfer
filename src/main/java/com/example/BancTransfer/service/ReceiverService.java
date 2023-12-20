package com.example.BancTransfer.service;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.transferDTO.Valuta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    @Autowired

    ContBancarRepository contBancarRepository;
    @Transactional
    public ResponseEntity<Object> receiverPerson(Integer idReceiver, Double amount, Valuta valuta)  {
        ContBancar contBancar = contBancarRepository.findByIdPersonAndCurrency(idReceiver,valuta.name());


            contBancar.setBalance(contBancar.getBalance() + amount);


        return ResponseEntity.ok("Ati primit un transfer in valoare de: " + amount);
    }
}
