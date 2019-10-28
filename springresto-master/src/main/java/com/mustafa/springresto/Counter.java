package com.mustafa.springresto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Counter {


    private @Id
    @GeneratedValue
    Long id;
    private int sayi;

  //  @OneToMany(targetEntity=Counter.class, mappedBy="college", fetch= FetchType.EAGER)
    @ElementCollection
    private List<Integer> liste =new ArrayList<Integer>();


/*
    Counter() {}

    Counter(int sayi) {
        this.sayi = sayi;


    }*/
}