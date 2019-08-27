package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.AccountRequest;
import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.entity.Client;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void shouldCreateReturnAccount() {
        AccountRequest request = AccountRequest.builder()
                .accountNumber(78785L)
                .balance(59896.3)
                .client(Client.builder()
                        .fistName("Bob")
                        .lastName("Marley")
                        .build())
                .build();

        AccountResponse response = accountService.createAccount(request);

        assertEquals(response.getBalance(), new Double(59896.3));
        assertEquals(response.getClient().getFistName(), "Bob");
        assertEquals(response.getClient().getLastName(), "Marley");
    }
}