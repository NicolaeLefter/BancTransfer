package com.example.BancTransfer;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.exception.ContBancarNotFoundException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.service.ReceiverService;
import com.example.BancTransfer.service.SendService;
import com.example.BancTransfer.service.TransferService;
import com.example.BancTransfer.transferDTO.TransferInputDTO;
import com.example.BancTransfer.transferDTO.Valuta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    ContBancarRepository contBancarRepository;

    @Autowired
    SendService sendService;
    @Autowired
    ReceiverService receiverService;
    @Autowired
    TransferService transferService;

   /* @BeforeEach
    public  void setup() {

        contBancarRepository.saveAll(List.of(
                new ContBancar(1, Valuta.LEI.name(), 500.00, 1),
                new ContBancar(2, Valuta.LEI.name(), 100.00, 2)


        ));
    } */


   /* @Test
    public void testeTransferMoney1() throws BalanceNegativException {
        // Stabiliți comportamentul mock-ului pentru senderService
        //  when(sendService.senderPerson(1, 200.00, Valuta.LEI))
        //     .thenReturn(new ContBancar(Valuta.LEI.name(), 600.00, 1));

        // Stabiliți comportamentul mock-ului pentru receiverService
        when(receiverService.receiverPerson(2, 200.00, Valuta.LEI))
                .thenReturn(ResponseEntity.ok("Transferul a fost efectuat cu succes!"));

        // Apelați metoda transferMoney
        ResponseEntity<Object> responseEntity = transferService.transferMoney(
                new TransferInputDTO(1, 2, 200.00, Valuta.LEI));

        // Verificați rezultatele așteptate
        Assertions.assertEquals(ResponseEntity.ok("Transferul a fost efectuat cu succes!"), responseEntity);

    }

    @Test
    public void testeTransferMoney() throws BalanceNegativException {

        //  ContBancar contBancar = new ContBancar(Valuta.LEI.name(), 5000.00, 1);
        // ContBancar contBancar1 = new ContBancar(Valuta.LEI.name(), 10000.00, 2);


        TransferInputDTO transferInputDTO = new TransferInputDTO(1, 2, 200.00, Valuta.LEI);
        when(contBancarRepository.findByIdPersonAndCurrency(1, Valuta.LEI.name()))
                .thenReturn(new ContBancar(Valuta.LEI.name(), 1000.0, 1));

        when(sendService.senderPerson(transferInputDTO.getIdSender(), transferInputDTO.getAmount(), transferInputDTO.getValuta())).
                thenReturn(ResponseEntity.ok("Transferul in valoare de: " + transferInputDTO.getAmount() + " a fost efectuat cu succes!"));

        when(receiverService.receiverPerson(transferInputDTO.getIdReceiver(), transferInputDTO.getAmount(), transferInputDTO.getValuta())).
                thenReturn(ResponseEntity.ok("Ati primit un transfer in valoare de: " + transferInputDTO.getAmount()));

        ResponseEntity<Object> response = transferService.transferMoney(transferInputDTO);

        Assertions.assertEquals(ResponseEntity.ok("Transferul a fost efectuat cu succes!"), response);

       /* ResponseEntity<Object> responseEntity = sendService.senderPerson(transferInputDTO.getIdSender(), transferInputDTO.getAmount(), transferInputDTO.getValuta());


        when(receiverService.receiverPerson(transferInputDTO.getIdReceiver(), transferInputDTO.getAmount(), transferInputDTO.getValuta()));
        ResponseEntity<Object> response = receiverService.receiverPerson(transferInputDTO.getIdReceiver(), transferInputDTO.getAmount(), transferInputDTO.getValuta());

        Assertions.assertEquals(ResponseEntity.ok("Transferul a fost efectuat cu succes!"), responseEntity);


    }    */

    @Test
    public void transferMoneyTest() throws BalanceNegativException {

        when(contBancarRepository.findByIdPersonAndCurrency(1, Valuta.LEI.name()))
                .thenReturn(new ContBancar(Valuta.LEI.name(), 1000.0, 1));

        // Apelați metoda testată
        ResponseEntity<Object> responseEntity = sendService.senderPerson(1, 100.0, Valuta.LEI);

        // Verificați rezultatele
        Assertions.assertEquals(ResponseEntity.ok("Transferul in valoare de: 100.0 a fost efectuat cu succes!"), responseEntity);

        when(contBancarRepository.findByIdPersonAndCurrency(2, Valuta.LEI.name())).
                thenReturn(new ContBancar(Valuta.LEI.name(), 500.00, 2));
        ResponseEntity<Object> response = receiverService.receiverPerson(2, 200.00, Valuta.LEI);
        Assertions.assertEquals(ResponseEntity.ok("Ati primit un transfer in valoare de: 200.0"), response);
    }








    @Test
    public void testeDeleteContByIdFalid() throws ContBancarNotFoundException {

        //Before

        Optional<ContBancar> contBancarBefore = contBancarRepository.findById(1);

        Assertions.assertTrue(contBancarBefore.isPresent()); // verificam ca contl bancar exista


        //Given

        int idCont = 10;
        ContBancarNotFoundException exception = assertThrows(ContBancarNotFoundException.class,
                () -> transferService.deleteContById(idCont));
        Assertions.assertEquals("Nu a fost gasit contul bancar cu id-ul: " + idCont, exception.getMessage(),
                "Mesajul de excepție nu corespunde așteptărilor.");



        //After
        Optional<ContBancar> contBancarAfter = contBancarRepository.findById(1);
        Assertions.assertTrue(contBancarAfter.isPresent());


    }




}