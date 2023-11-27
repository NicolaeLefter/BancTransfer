package com.example.BancTransfer.service;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.transferDTO.Valuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired

    ContBancarRepository contBancarRepository;

    public ResponseEntity<Object> senderPerson(Integer idSender, Double amount, Valuta valuta){
        ContBancar contBancar = contBancarRepository.findByIdPersonAndCurrency(idSender, valuta.name());
        contBancar.setBalance(contBancar.getBalance() - amount);
        return ResponseEntity.ok("Transferul in valoare de: " + amount + " a fost efectuat cu succes!");
    }
}
