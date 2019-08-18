package com.leovegas.apiwallet.domain;

import com.leovegas.apiwallet.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class AccountResponse {

    private long accountNumber;

    private long balance;

    private Client client;
}
