package com.mustafa.springresto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Counter {


    private @Id
    @GeneratedValue
    Long id;
    private int sayi;


    Counter() {}

    Counter(int sayi) {
        this.sayi = sayi;

    }
}