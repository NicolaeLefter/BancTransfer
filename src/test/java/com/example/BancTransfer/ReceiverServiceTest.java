package com.example.BancTransfer;

import com.example.BancTransfer.entity.ContBancar;
import com.example.BancTransfer.exception.BalanceNegativException;
import com.example.BancTransfer.repository.ContBancarRepository;
import com.example.BancTransfer.service.ReceiverService;
import com.example.BancTransfer.transferDTO.Valuta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ReceiverServiceTest {

    @MockBean
    ContBancarRepository contBancarRepository;
    @Autowired
    ReceiverService receiverService;
   @Test
    public void testeReceiverService() throws BalanceNegativException {
        when(contBancarRepository.findByIdPersonAndCurrency(2, Valuta.LEI.name())).
                thenReturn(new ContBancar(Valuta.LEI.name(), 500.00, 2));
        ResponseEntity<Object> responseEntity = receiverService.receiverPerson(2, 200.00, Valuta.LEI);
        Assertions.assertEquals(ResponseEntity.ok("Ati primit un transfer in valoare de: 200.0"), responseEntity);
    }

   /* @Test
    public void testSender() throws BalanceNegativException {
        // Definiți comportamentul mock-ului pentru repository
        when(contBancarRepository.findByIdPersonAndCurrency(1, Valuta.LEI.name()))
                .thenReturn(new ContBancar(Valuta.LEI.name(), 1000.0, 1));

        // Apelați metoda testată
        ResponseEntity<Object> responseEntity = sendService.senderPerson(1, 100.0, Valuta.LEI);

        // Verificați rezultatele
        Assertions.assertEquals(ResponseEntity.ok("Transferul in valoare de: 100.0 a fost efectuat cu succes!"), responseEntity);
    } */


}
