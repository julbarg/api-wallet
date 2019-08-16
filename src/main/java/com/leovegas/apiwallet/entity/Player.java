package com.leovegas.apiwallet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private long id;

    private String fistName;

    private String lastName;

    public Player(String fistName, String lastName) {
        this.fistName = fistName;
        this.lastName = lastName;
    }


}
