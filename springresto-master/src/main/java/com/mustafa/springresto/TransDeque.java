package com.mustafa.springresto;

import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Data


public class TransDeque {


    private @Id @GeneratedValue Long id;
    private Vehicle vehicle= new Vehicle();

    private Deque<Vehicle> deque=new LinkedList<>();


    /*
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "TransDeque", orphanRemoval = true)

    public Deque<Vehicle> getDeque(){return deque;}
    public void setDeque (Deque<Vehicle> deque){this.deque=deque;}
    private Deque<Vehicle> deque;

*/



}
