package com.mustafa.springresto;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class SpringrestoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringrestoApplication.class, args);

        System.out.println("asdasdasd");

        System.out.println("asdasdasd");


        System.out.println("asdasdasd");

        Thread thread =new Thread(new Runnable() {

            int asd=0;

            @Override
            public void run() {
            while(true){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(asd);
                asd++;
                asd++;

            }}
        });

        thread.start();


    }

}
