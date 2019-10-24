package com.mustafa.springresto;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class SpringrestoApplication {
    static int asd=0;
    static Counter counter = new Counter(asd);

    public static void main(String[] args) {
        SpringApplication.run(SpringrestoApplication.class, args);

        System.out.println("asdasdasd");

        System.out.println("asdasdasd");


        System.out.println("asdasdasd");

        Thread thread =new Thread(new Runnable() {



            //Counter counter = new Counter(asd)     ;
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

                counter.setSayi(asd);

            }}
        });

        thread.start();


    }

}
