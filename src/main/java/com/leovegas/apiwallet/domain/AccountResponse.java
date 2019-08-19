package com.leovegas.apiwallet.domain;

import com.leovegas.apiwallet.entity.Client;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class AccountResponse {

    private Long accountNumber;

    private Double balance;

    private Client client;
}
