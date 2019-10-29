package com.mustafa.springresto;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CounterController {


    @RequestMapping(value="/all")
        public  ResponseEntity<Counter>get(){
        Counter counter=new Counter();
        counter.setSayi(SpringrestoApplication.counter.getSayi());
        counter.setListe(SpringrestoApplication.counter.getListe());


        return new ResponseEntity<Counter>(counter, HttpStatus.OK);
    }

    @RequestMapping(value="/allpost")
    @ResponseBody

    public  ResponseEntity<Counter>set(){

            Counter counter=SpringrestoApplication.counter;
            counter.setSayi(1000);
            counter.getListe().add(1000);
            counter.setListe(counter.getListe());
            return new ResponseEntity<Counter>(counter, HttpStatus.OK);
        }
}

/*

    @RequestMapping("/counter")
    public int asd() {

        return SpringrestoApplication.counter.getSayi();


    }

    @RequestMapping("/counterList")
    public ArrayList<Integer> liste() {

        return SpringrestoApplication.liste;


    }

//(value="sayi", defaultValue= "0" ) int sayi
*/
