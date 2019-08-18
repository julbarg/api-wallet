package com.leovegas.apiwallet.resources;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.exception.TransactionNotFoundException;
import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<Object> createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{transactionId}")
                .buildAndExpand(transaction.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
