package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionType;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;



    @Test(expected = CompletionException.class)
    public void shouldReturnInsufficientFundException() {
        long transactionId = 7895L;
        Account account = Account.builder()
                .accountNumber(564656L)
                .balance(25.0)
                .build();

        when(accountRepository.findByAccountNumber(anyLong()))
                .thenReturn(account);
        when(transactionRepository.existsByTransactionId(transactionId))
                .thenReturn(Boolean.FALSE);

        TransactionRequest request = TransactionRequest.builder()
                .amount(250.0)
                .transactionType("DEBIT")
                .transactionId(transactionId)
                .build();

        CompletableFuture<Transaction> transaction = transactionService.createTransaction(564656L, request);
        transaction.join();
    }

    @Test
    public void shouldCreateAndReturnTransaction() {
        long transactionId = 7895L;
        Account account = Account.builder()
                .accountNumber(564656L)
                .balance(25.0)
                .build();

        when(accountRepository.findByAccountNumber(anyLong()))
                .thenReturn(account);
        when(transactionRepository.existsByTransactionId(transactionId))
                .thenReturn(Boolean.FALSE);

        TransactionRequest request = TransactionRequest.builder()
                .amount(250.0)
                .transactionType("CREDIT")
                .transactionId(transactionId)
                .build();

        Transaction transaction = transactionService.createTransaction(58789, request).join();
        assertEquals(transaction.getTransactionId().longValue(), transactionId);
        assertEquals(transaction.getAmount(), new Double(250.0));
        assertEquals(transaction.getAccount().getBalance(), new Double(275.0));
    }

    @Test
    public void shouldRetrieveTransactions() {
        Account account = Account.builder()
                .accountNumber(564656L)
                .balance(25.0)
                .transactions(getSetTransactions(3))
                .build();

        when(accountRepository.findByAccountNumber(anyLong()))
                .thenReturn(account);

        when(transactionRepository.findByAccount(account))
                .thenReturn(account.getTransactions());

        assertEquals(transactionService.retrieveHistoryTransaction(564656L).join().size(), 3);
    }

    private Set<Transaction> getSetTransactions(int numberOfItems) {
        Set<Transaction> transactions = new HashSet<>();
        Random rn = new Random();

        for (int i = 0; i < numberOfItems; i++) {
            double amount = rn.nextDouble() * 100.0;

            Transaction transaction = Transaction.builder()
                    .transactionType(TransactionType.DEBIT)
                    .amount(amount)
                    .build();
            transactions.add(transaction);
        }

        return transactions;
    }
}