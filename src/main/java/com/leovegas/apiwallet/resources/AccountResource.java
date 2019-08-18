package com.leovegas.apiwallet.resources;

import com.leovegas.apiwallet.domain.AccountRequest;
import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.service.AccountService;
import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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
    public AccountResponse retrieveAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/{accountNumber}")
    @ApiOperation("Retrieve account details")
    public AccountResponse retrieveAccount(@PathVariable long accountNumber) {
        return accountService.getAccountDetail(accountNumber);
    }

    @PostMapping("/{accountNumber}/transaction")
    @ApiOperation("Create transaction by accountNumber")
    public MappingJacksonValue createTransaction(@PathVariable long accountNumber, @Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(accountNumber, request);
        TransactionResponse response = getTransactionResponseMapper(transaction);

        return getFilterMappingTransaction(response, null);
    }

    @GetMapping("/{accountNumber}/history")
    @ApiOperation("Retrieve transactions history by account number")
    public MappingJacksonValue retireveHistoryAccount(@PathVariable long accountNumber) {
        Set<TransactionResponse> transactionResponses = transactionService.retrieveHistoryTransaction(accountNumber);

        return getFilterMappingTransaction(transactionResponses, "account");
    }
}
