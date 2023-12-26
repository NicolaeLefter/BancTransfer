package com.example.BancTransfer.service;

import com.example.BancTransfer.controller.exersari.ServiceSum;
import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.transferDTO.Valuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SendService {
    @Autowired
    ContBancarRepository contBancarRepository;
    @Autowired
    ServiceSum serviceSum;
    @Transactional //(propagation = Propagation.MANDATORY, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ResponseEntity<Object> senderPerson(Integer idSender, Double amount, Valuta valuta) throws BalanceNegativException {
        ContBancar contBancar = contBancarRepository.findByIdPersonAndCurrency(idSender, valuta.name());
        if (contBancar.getBalance() < amount){
            throw  new BalanceNegativException("Nu aveti suficienti bani pe cont pentru a efectua transferul!");
        }else if (amount > 8000){
            throw  new RuntimeException("Suma transfersului depaseste limita admisibila de 8000 lei/zi!");

        }
        contBancar.setBalance(contBancar.getBalance() - amount);
        return ResponseEntity.ok("Transferul in valoare de: " + amount + " a fost efectuat cu succes!");
    }


    public ContBancar findContBancarById(Integer id){

        Optional<ContBancar> contBancar = contBancarRepository.findById(id);
        return contBancar.get();
    }

    public Integer sum(){
      Integer result =   serviceSum.adunare(2,2);

      return result;
    }

    public Integer calcularMedieContBancar(){
      List<ContBancar> contBancarLis =   contBancarRepository.findAll();

      int sum = 0;

      for(ContBancar contBancar : contBancarLis){
         sum +=  contBancar.getBalance();
      }
     if (contBancarLis.size() == 0){
         return 0;
     }
      return sum / contBancarLis.size();


    }


}
