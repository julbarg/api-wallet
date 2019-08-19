package com.leovegas.apiwallet.resources;

import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.leovegas.apiwallet.util.TransactionUtil.getFilterMappingTransaction;
import static com.leovegas.apiwallet.util.TransactionUtil.getTransactionResponseMapper;

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

        TransactionResponse response = getTransactionResponseMapper(transaction);

        return getFilterMappingTransaction(response, null);
    }
}
