package com.mustafa.springresto;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Vehicle {


    @Id @GeneratedValue
    private int id;
    private String v_title;
    private String v_category;


    Vehicle() {}

    Vehicle(String v_title,String v_category){

        this.v_title=v_title;
        this.v_category=v_category;


    }


}
