package com.leovegas.apiwallet.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import org.springframework.http.converter.json.MappingJacksonValue;

public class TransactionUtil {

    public static MappingJacksonValue getFilterMappingTransaction(Object value, String propertyToRemoved) {
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

    public static TransactionResponse getTransactionResponseMapper(Transaction transaction) {
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
