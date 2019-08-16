package com.leovegas.apiwallet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Player {

    @ToString.Exclude
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
