package com.leovegas.apiwallet.resources;

import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @GetMapping("/wallet/account/{accountNumber}")
    public AccountResponse retrieveAccount(@PathVariable long accountNumber) {
        return accountService.getAccountDetail(accountNumber);
    }
}
