package com.example.BancTransfer;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.service.SendService;
import com.example.BancTransfer.controller.exersari.ServiceSum;
import com.example.BancTransfer.transferDTO.Valuta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SendServiceTest {


    @MockBean
    ContBancarRepository contBancarRepository;


    @Autowired
    SendService sendService;
    @MockBean
    ServiceSum serviceSum;


    @Test
    public void testSenderPerson() throws BalanceNegativException {
        // Definiți comportamentul mock-ului pentru repository
        when(contBancarRepository.findByIdPersonAndCurrency(1, Valuta.LEI.name()))
                .thenReturn(new ContBancar(Valuta.LEI.name(), 1000.0, 1));

        // Apelați metoda testată
        ResponseEntity<Object> responseEntity = sendService.senderPerson(1, 100.0, Valuta.LEI);

        // Verificați rezultatele
       Assertions.assertEquals(ResponseEntity.ok("Transferul in valoare de: 100.0 a fost efectuat cu succes!"), responseEntity);
    }
    @Test
    public void findContBancarMock(){



        when(contBancarRepository.findById(anyInt())).thenReturn(Optional.of(new ContBancar(10)));

          ContBancar contBancar = sendService.findContBancarById(3);

          Assertions.assertEquals(10, contBancar.getIdCont());

    }
   @Test
    public void test2Plus2(){

        when(serviceSum.adunare(anyInt(),anyInt())).thenReturn(3);

    Integer result = sendService.sum();
    Assertions.assertEquals(3, result);


    }
   @Test // cand testam separat o metoda daca solosim si mock este JUNIT test cu mock
    public void testCalculareMedie(){

        List<ContBancar> contBancarList = new ArrayList<>();

        contBancarList.add(new ContBancar(1, Valuta.LEI.name(), 1000.00, 1));
        contBancarList.add(new ContBancar(2, Valuta.LEI.name(), 5000.00, 2));
        contBancarList.add(new ContBancar(3, Valuta.LEI.name(), 7000.00, 3));

        when(contBancarRepository.findAll()).thenReturn(contBancarList);

       Integer result =  sendService.calcularMedieContBancar();

       Assertions.assertEquals(4333,result);

    }

    @Test
    public void testCalculareMedieImpartireLa0(){

        List<ContBancar> contBancarList = new ArrayList<>();

        when(contBancarRepository.findAll()).thenReturn(contBancarList);

        Integer result =  sendService.calcularMedieContBancar();

        Assertions.assertEquals(0,result);

    }

   /* @Service
    public void testSendPerson() throws BalanceNegativException {

        ContBancar contBancar = new ContBancar(Valuta.LEI.name(), 500.00, 1);

        TransferInputDTO transferInputDTO = new TransferInputDTO(1, 2, 100.00, Valuta.LEI);
        if (contBancar.getBalance() < transferInputDTO.getAmount()) {
            throw new BalanceNegativException("Nu aveti suficienti bani pe cont pentru a efectua transferul!");
        } else if (contBancar.getBalance() >= transferInputDTO.getAmount()) {
            throw new RuntimeException("Suma transfersului depaseste limita admisibila de 8000 lei/zi!");


        }
    }

    /*@Test
    public void testTransferMoney() throws BalanceNegativException {

        ContBancar contBancar = new ContBancar();

        when(sendService.senderPerson(1, 100.0, Valuta.LEI)).thenReturn();
        when(receiverService.receiverPerson(2, 100.0, Valuta.LEI)).thenReturn(receiverService.receiverPerson(2, 100.0, Valuta.LEI));

        // Apelăm metoda transferMoney
        ResponseEntity<Object> responseEntity = transferService.transferMoney(
                new TransferInputDTO(1, 2, 100.0, Valuta.LEI));

        // Verificăm rezultatele așteptate
       Assertions.assertEquals(ResponseEntity.ok("Transferul a fost efectuat cu succes!"), responseEntity);
    }

   /* @Test
    public void testTransferMoney() throws BalanceNegativException {

        sendService.senderPerson(1, 250.0, Valuta.LEI);
        receiverService.receiverPerson(2, 250.0, Valuta.LEI);

        ResponseEntity<Object> responseEntity = transferService.transferMoney(new TransferInputDTO(1, 2, 250.0, Valuta.LEI));

        Assertions.assertEquals(ResponseEntity.ok("Transfer efectuat cu succes!"), responseEntity);


    } */




}
