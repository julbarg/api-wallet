package com.leovegas.apiwallet.resources;

import com.leovegas.apiwallet.domain.AccountRequest;
import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.service.AccountService;
import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

import static com.leovegas.apiwallet.util.TransactionUtil.getFilterMappingTransaction;
import static com.leovegas.apiwallet.util.TransactionUtil.getTransactionResponseMapper;

@RestController
@RequestMapping("/wallet/account")
@Api("Set of endpoint to retrieve account information")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ApiOperation("Create account")
    @Async
    public CompletableFuture<ResponseEntity> retrieveAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request)
                .thenApply(accountResponse -> new ResponseEntity(accountResponse, HttpStatus.CREATED));
    }

    @GetMapping("/{accountNumber}")
    @ApiOperation("Retrieve account details")
    @Async
    public CompletableFuture<ResponseEntity> retrieveAccount(@PathVariable long accountNumber) {
        return accountService.getAccountDetail(accountNumber)
                .thenApply(accountResponse -> new ResponseEntity(accountResponse, HttpStatus.OK));
    }

    @PostMapping("/{accountNumber}/transaction")
    @ApiOperation("Create transaction by accountNumber")
    @Async
    public CompletableFuture<ResponseEntity> createTransaction(@PathVariable long accountNumber, @Valid @RequestBody TransactionRequest request) {
        return transactionService.createTransaction(accountNumber, request)
                .thenApply(transaction -> {
                    TransactionResponse response = getTransactionResponseMapper(transaction);

                    MappingJacksonValue filterMappingTransaction = getFilterMappingTransaction(response, null);

                    return new ResponseEntity(filterMappingTransaction, HttpStatus.CREATED);
                });
    }

    @GetMapping("/{accountNumber}/history")
    @ApiOperation("Retrieve transactions history by account number")
    @Async
    public CompletableFuture<ResponseEntity> retireveHistoryAccount(@PathVariable long accountNumber) {
        return transactionService.retrieveHistoryTransaction(accountNumber)
                .thenApply(transactionResponses -> {
                    MappingJacksonValue filterMappingTransaction = getFilterMappingTransaction(transactionResponses, "account");

                    return new ResponseEntity(filterMappingTransaction, HttpStatus.OK);
                });
    }
}
