package com.leovegas.apiwallet.resources;

import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.exception.TransactionNotFoundException;
import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/wallet/transaction")
@Api("Set of endpoint to manage transactions")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{transactionId}")
    @ApiOperation("Retrieve transaction by transactionId")
    public Transaction retrieveTransaction(@PathVariable long transactionId) {
        Transaction transaction = transactionService.retrieveTransaction(transactionId);

        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction Not Found id-"+transactionId);
        }

        return transaction;
    }


    @PostMapping
    @ApiOperation("Create transaction")
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request);
        Account account = transaction.getAccount();

        AccountResponse accountResponse = AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .client(account.getClient())
                .build();

        TransactionResponse response = TransactionResponse.builder()
                .account(accountResponse)
                .date(transaction.getDate())
                .transactionId(transaction.getTransactionId())
                .transactionType(transaction.getTransactionType().name())
                .amount(transaction.getAmount())
                .build();

        return response;
    }
}
