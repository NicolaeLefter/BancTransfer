package com.example.BancTransfer.service;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.exception.ContBancarNotFoundException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.transferDTO.TransferInputDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    ReceiverService receiverService;
    @Autowired

    SendService sendService;
    @Autowired

    ContBancarRepository contBancarRepository;

    @Transactional
    public ResponseEntity<Object> transferMoney(TransferInputDTO transferInputDTO) throws BalanceNegativException {

        sendService.senderPerson(transferInputDTO.getIdSender(), transferInputDTO.getAmount(), transferInputDTO.getValuta());
        receiverService.receiverPerson(transferInputDTO.getIdReceiver(), transferInputDTO.getAmount(), transferInputDTO.getValuta());
        return ResponseEntity.ok("Transferul a fost efectuat cu succes!");

    }

    @Transactional
    public ContBancar deleteContById(Integer idCont) throws ContBancarNotFoundException {
        try {
            contBancarRepository.findById(idCont);
        } catch (Exception e) {
            throw new ContBancarNotFoundException("Nu a fost gasit contul cu id-ul: " + idCont + e.getMessage());
        }
        return contBancarRepository.deleteByIdCont(idCont);


    }



    @Transactional
    public ResponseEntity<Object> updateContBancar(ContBancar contBancar, Integer idCont) throws ContBancarNotFoundException {
        ContBancar cont = contBancarRepository.findById(idCont).orElseThrow(() ->
                new ContBancarNotFoundException("Nu a fost gasit contul cu id-ul: " + idCont));

        Map<Object, String> map = new HashMap<>();

        if (contBancar.getCurrency() == null) {
            map.put(contBancar.getCurrency(), "Campul currency nu poate fi null!");
        } else {
            cont.setCurrency(contBancar.getCurrency());

            if (contBancar.getBalance() < 0) {
                map.put(contBancar.getBalance(), "Balanta contului nu poate fi una negativa!");
            } else {
                cont.setBalance(contBancar.getBalance());
                cont.setIdPerson(contBancar.getIdPerson());
            }
        }

        if (!map.isEmpty()) {
            throw new RuntimeException("A aparut o eroare la actualizarea contului!");
        }

        contBancarRepository.save(cont);

        return ResponseEntity.ok("Contul a fost actualizat cu succes!");
    }


}
