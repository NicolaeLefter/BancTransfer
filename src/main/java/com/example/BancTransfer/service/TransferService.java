package com.example.BancTransfer.service;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.ContBancarNotFoundException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.transferDTO.TransferInputDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<Object> transferMoney(TransferInputDTO transferInputDTO) {

        sendService.senderPerson(transferInputDTO.getIdSender(), transferInputDTO.getAmount(), transferInputDTO.getValuta());
        receiverService.receiverPerson(transferInputDTO.getIdReceiver(), transferInputDTO.getAmount(), transferInputDTO.getValuta());
        return ResponseEntity.ok("Transferul a fost efectuat cu succes!");

    }

    @Transactional
    public ResponseEntity<Object> deleteContById(Integer idCont) throws ContBancarNotFoundException {
        try {
            contBancarRepository.findById(idCont);
        } catch (Exception e) {
            throw new ContBancarNotFoundException("Nu a fost gasit contul bancar cu id-ul: " + idCont);
        }
        contBancarRepository.deleteByIdCont(idCont);
        return ResponseEntity.ok("Contul bancar cu id- ul " + idCont + " a fost stersa cu succes!");

    }

}
