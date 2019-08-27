package com.leovegas.apiwallet.resources;

import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.leovegas.apiwallet.util.TransactionUtil.getFilterMappingTransaction;

@RestController
@RequestMapping("/wallet/transaction")
@Api("Set of endpoint to manage transactions")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{transactionId}")
    @ApiOperation("Retrieve transaction by transactionId")
    @Async
    public CompletableFuture<ResponseEntity> retrieveTransaction(@PathVariable long transactionId) {
        return transactionService.retrieveTransaction(transactionId)
                .thenApply(transactionResonse -> {
                    MappingJacksonValue filterMappingTransaction = getFilterMappingTransaction(transactionResonse, null);

                    return new ResponseEntity(filterMappingTransaction, HttpStatus.OK);
                });
    }
}
