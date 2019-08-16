package com.leovegas.apiwallet.domain;

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

    private String firsName;

    private String lastName;

    private long accountNumber;

    private long balance;

    private long playerId;
}
