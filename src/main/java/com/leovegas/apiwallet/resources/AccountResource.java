package com.leovegas.apiwallet.resources;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.service.AccountService;
import com.leovegas.apiwallet.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/wallet/account")
@Api("Set of endpoint to retrieve account information")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountNumber}")
    @ApiOperation("Retrieve account details")
    public AccountResponse retrieveAccount(@PathVariable long accountNumber) {
        return accountService.getAccountDetail(accountNumber);
    }

    @GetMapping("/{accountNumber}/history")
    @ApiOperation("Retrieve transactions history by account number")
    public MappingJacksonValue retireveHistoryAccount(@PathVariable long accountNumber) {
        Set<TransactionResponse> transactionResponses = transactionService.retrieveHistoryTransaction(accountNumber);

        return getFilterMappingTransaction(transactionResponses, "account");
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
}
