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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContBancarRepositoryTest {

    @Autowired
    ContBancarRepository contBancarRepository;
    @Autowired
    SendService sendService;
    @Autowired
    TransferService transferService;
    @Autowired
    ReceiverService receiverService;

    @Test //Junit teste TESTAM CU DATE REALE
    public void testeAddContBancar() {
        //Before
        List<ContBancar> contBancarBefore = contBancarRepository.findAll();

        Assertions.assertEquals(3, contBancarBefore.size());
        Assertions.assertFalse(contBancarBefore.contains(new ContBancar(4)));

        //Given
        ContBancar contBancar = new ContBancar(4);
        contBancarRepository.save(contBancar);

        //After
        List<ContBancar> contBancarAfter = contBancarRepository.findAll();
        Assertions.assertEquals(4, contBancarAfter.size());
        Assertions.assertTrue(contBancarAfter.contains(new ContBancar(4)));


    }


    @Test
    void testDeleteContByIdSucces() throws ContBancarNotFoundException {
        //  Before - inainte
        ContBancar contBancar = contBancarRepository.findById(1).get(); //oferim contul care dorim sal gasim
        Assertions.assertEquals(1, contBancar.getIdCont()); //verificam ca exista
        //Given - avand(ce facem noi)
        transferService.deleteContById(1); // stergem contul

        //After
        Optional<ContBancar> optional = contBancarRepository.findById(1); // dam ce cont sa gasim

        Assertions.assertFalse(optional.isPresent()); // verificam ca contul nu exista


    }

    @Test
    public void updateContBancarTestSucces() throws ContBancarNotFoundException {

        //Before
        ContBancar contBefore = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBefore.getCurrency());
        Assertions.assertEquals(500.00, contBefore.getBalance());
        Assertions.assertEquals(1, contBefore.getIdPerson());

        ContBancar contBancar = new ContBancar(Valuta.EUR.name(), 1000.00, 1);
        //Given
        transferService.updateContBancar(contBancar, 1);

        //After
        ContBancar contAfter = contBancarRepository.findById(1).get();

        Assertions.assertEquals(Valuta.EUR.name(), contAfter.getCurrency());
        Assertions.assertEquals(1000.00, contAfter.getBalance());
        Assertions.assertEquals(1, contAfter.getIdPerson());

    }

    @Test
    public void updateContBancarTestError() throws ContBancarNotFoundException {

        //Before
        ContBancar contBancarBefore = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarBefore.getCurrency());
        Assertions.assertEquals(500.00, contBancarBefore.getBalance());
        Assertions.assertEquals(1, contBancarBefore.getIdPerson());

        ContBancar contBancar = new ContBancar(Valuta.EUR.name(), 800.00, 1);
        //Given
        assertThrows(ContBancarNotFoundException.class, () -> transferService.updateContBancar(contBancar, 19));


        //After

        ContBancar contBancarAfter = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarAfter.getCurrency());
        Assertions.assertEquals(500.00, contBancarAfter.getBalance());
        Assertions.assertEquals(1, contBancarAfter.getIdPerson());


    }

    @Test
    public void testReceiverPerson() throws BalanceNegativException {

        //Before
        ContBancar contBancarReceiver = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarReceiver.getCurrency());
        Assertions.assertEquals(500.00, contBancarReceiver.getBalance());
        Assertions.assertEquals(1, contBancarReceiver.getIdPerson());

        Double amount = 100.00;

        //Give
        receiverService.receiverPerson(contBancarReceiver.getIdPerson(), amount, Valuta.LEI);
        contBancarReceiver.setBalance(contBancarReceiver.getBalance() + amount);


        //After

        ContBancar contBancarReceiverAfter = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarReceiverAfter.getCurrency());
        Assertions.assertEquals(600.00, contBancarReceiverAfter.getBalance());
        Assertions.assertEquals(1, contBancarReceiverAfter.getIdPerson());

    }




   /* @Test
    public void testSendPerson() throws BalanceNegativException {

        //Befor

        ContBancar contBancarSender = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarSender.getCurrency());
        Assertions.assertEquals(500.00, contBancarSender.getBalance());
        Assertions.assertEquals(1, contBancarSender.getIdPerson());

        Double amount = 100.00;

        //Given
        sendService.senderPerson(contBancarSender.getIdPerson(), amount, Valuta.LEI);
        contBancarSender.setBalance(contBancarSender.getBalance() - amount);

        //After

        ContBancar contBancarSenderAfter = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarSenderAfter.getCurrency());
        Assertions.assertEquals(400.00, contBancarSenderAfter.getBalance());
        Assertions.assertEquals(1, contBancarSenderAfter.getIdPerson());


    } */
    @Test
    public void testSendPersonFalid() throws BalanceNegativException {
        // Before
        ContBancar contBancarSender = contBancarRepository.findById(4).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarSender.getCurrency());
        Assertions.assertEquals(-100.00, contBancarSender.getBalance());
        Assertions.assertEquals(3, contBancarSender.getIdPerson());

        Double amount = 100.00;

        // Give
        assertThrows(BalanceNegativException.class, ()-> sendService.senderPerson(contBancarSender.getIdPerson(), amount, Valuta.LEI));

        // After
        ContBancar contBancarSenderAfter = contBancarRepository.findById(4).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarSenderAfter.getCurrency());
        Assertions.assertEquals(-100.00, contBancarSenderAfter.getBalance());
        Assertions.assertEquals(3, contBancarSenderAfter.getIdPerson());
    }

    @Test
    public void testTransferMoneySucces() throws BalanceNegativException {
        //After

        ContBancar contBancarSender = contBancarRepository.findById(4).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarSender.getCurrency());
        Assertions.assertEquals(500.00, contBancarSender.getBalance());
        Assertions.assertEquals(1, contBancarSender.getIdPerson());

        ContBancar contBancarReceiver = contBancarRepository.findById(2).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarReceiver.getCurrency());
        Assertions.assertEquals(600.00, contBancarReceiver.getBalance());
        Assertions.assertEquals(2, contBancarReceiver.getIdPerson());

        Double amount = 100.00;

        TransferInputDTO transferInputDTO =
                new TransferInputDTO(contBancarSender.getIdPerson(), contBancarReceiver.getIdPerson(), amount, Valuta.LEI);
        //Given

        transferService.transferMoney(transferInputDTO);

        //After
        ContBancar contBancarSenderAfter = contBancarRepository.findById(1).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarSenderAfter.getCurrency());
        Assertions.assertEquals(400.00, contBancarSenderAfter.getBalance());
        Assertions.assertEquals(1, contBancarSenderAfter.getIdPerson());

        ContBancar contBancarReceiverAfter = contBancarRepository.findById(2).get();
        Assertions.assertEquals(Valuta.LEI.name(), contBancarReceiverAfter.getCurrency());
        Assertions.assertEquals(700.00, contBancarReceiverAfter.getBalance());
        Assertions.assertEquals(2, contBancarReceiverAfter.getIdPerson());


    }


}
