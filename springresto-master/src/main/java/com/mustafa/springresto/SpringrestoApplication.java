package com.mustafa.springresto;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


@SpringBootApplication
public class SpringrestoApplication {

    static Counter counter = new Counter();
    static  TransDeque transDeque=new TransDeque();

    public static void main(String[] args) {
        SpringApplication.run(SpringrestoApplication.class, args);


        System.out.println("asdasdasd");

        System.out.println("asdasdasd");


        System.out.println("asdasdasd");

        Vehicle vehic=new Vehicle();
        vehic.setV_title("asdasd");
        vehic.setV_category("cateeg");



        Thread thread =new Thread(new Runnable() {

            @Override
            public void run() {
                //counter.setSayi(0);


            while(true){

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int asd=counter.getSayi();


                System.out.println(asd);

                System.out.println(transDeque.getDeque());
                asd++;
                asd++;
              counter.getListe().add(asd);

                counter.setSayi(asd);
                counter.setListe(counter.getListe());

            }}
        });

        thread.start();


    }

}
