package com.leovegas.apiwallet.resources;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/wallet/transaction")
@Api("Set of endpoint to manage transactions")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{transactionId}")
    @ApiOperation("Retrieve transaction by transactionId")
    public MappingJacksonValue retrieveTransaction(@PathVariable long transactionId) {
        Transaction transaction = transactionService.retrieveTransaction(transactionId);

        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction Not Found id: " + transactionId);
        }

        TransactionResponse response = getTransactionResponseMapper(transaction);

        return getFilterMappingTransaction(response, null);
    }

    @GetMapping("/{accountNumber}/history")
    @ApiOperation("Retrieve transactions history by account number")
    public MappingJacksonValue retireveHistoryAccount(@PathVariable long accountNumber) {
        Set<TransactionResponse> transactionResponses = transactionService.retrieveHistoryTransaction(accountNumber);

        return getFilterMappingTransaction(transactionResponses, "account");
    }

    @PostMapping
    @ApiOperation("Create transaction")
    public MappingJacksonValue createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request);
        TransactionResponse response = getTransactionResponseMapper(transaction);

        return getFilterMappingTransaction(response, null);
    }

    private MappingJacksonValue getFilterMappingTransaction(Object value, String propertyToRemoved) {
        SimpleBeanPropertyFilter filter;
        FilterProvider filters;
        MappingJacksonValue mapping = new MappingJacksonValue(value);

        if (propertyToRemoved != null) {
            filter = SimpleBeanPropertyFilter.serializeAllExcept(propertyToRemoved);
        } else {
            filter = SimpleBeanPropertyFilter.serializeAll();
        }

        filters = new SimpleFilterProvider().addFilter("TransactionFilter", filter);
        mapping.setFilters(filters);

        return mapping;
    }

    private TransactionResponse getTransactionResponseMapper(Transaction transaction) {
        Account account = transaction.getAccount();

        AccountResponse accountResponse = AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .client(account.getClient())
                .build();

        return TransactionResponse.builder()
                .account(accountResponse)
                .date(transaction.getDate())
                .transactionId(transaction.getTransactionId())
                .transactionType(transaction.getTransactionType().name())
                .amount(transaction.getAmount())
                .build();

    }
}
