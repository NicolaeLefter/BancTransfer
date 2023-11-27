package com.example.BancTransfer.controller;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.ContBancarNotFoundException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.service.TransferService;
import com.example.BancTransfer.transferDTO.TransferInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transfer")
public class ContBancarController {

    @Autowired

    ContBancarRepository contBancarRepository;
    @Autowired

    TransferService transferService;
   @PostMapping("/save")
    public ResponseEntity<Object> saveContBancar(@RequestBody ContBancar contBancar){
        return ResponseEntity.status(HttpStatus.OK).body(contBancarRepository.save(contBancar));
    }
    @PostMapping("/save/all")
    public ResponseEntity<Object> saveAllContBancar(@RequestBody List<ContBancar> contBancarList){
       return ResponseEntity.status(HttpStatus.OK).body(contBancarRepository.saveAll(contBancarList));
    }

    @DeleteMapping("/delete/byId/{idCont}")
    public ResponseEntity<Object> deleteContById(@PathVariable Integer idCont) throws ContBancarNotFoundException {
       return ResponseEntity.status(HttpStatus.OK).body(transferService.deleteContById(idCont));
    }
    @PutMapping("transferMoney")
    public ResponseEntity<Object> transferMoney(@RequestBody TransferInputDTO transferInputDTO){
       return ResponseEntity.status(HttpStatus.OK).body(transferService.transferMoney(transferInputDTO));
    }
    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll(){
       return ResponseEntity.status(HttpStatus.OK).body(contBancarRepository.findAll());
    }
    @DeleteMapping("/delete/{idCont}")
    public void deleteCont(@PathVariable Integer idCont){
       contBancarRepository.deleteById(idCont);
    }


}
