package com.mustafa.springresto;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
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
        List<Vehicle> vehicleList=new ArrayList<Vehicle>();
        Vehicle vehic=new Vehicle(1,"otobüs","4");
        Vehicle vehic2=new Vehicle(2,"araba","2");
        Vehicle vehic3=new Vehicle(3,"kamyon","3");
        Vehicle vehic4=new Vehicle(4,"minibüs","1");
        Vehicle vehic5=new Vehicle(5,"kamyon","3");
        Vehicle vehic6=new Vehicle(6,"minibüs","1");
        vehicleList.add(vehic);
        vehicleList.add(vehic2);
        vehicleList.add(vehic3);
        vehicleList.add(vehic4);
        vehicleList.add(vehic5);
        vehicleList.add(vehic6);

        Thread thread =new Thread(new Runnable() {
            int aracSayisi=0;
            @Override
            public void run() {
                //counter.setSayi(0);


            while(aracSayisi<6){

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                addVehicle(vehicleList.get(aracSayisi));

                aracSayisi++;



                while(aracSayisi>2){

                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    removeVehicle();

                    aracSayisi--;



                }



            }












            }
        });

        thread.start();


    }

    static public void addVehicle(Vehicle vehicle)
    {
        Deque<Vehicle> deque= SpringrestoApplication.transDeque.getDeque();
        deque.addFirst(vehicle);

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:5000/VehicleAdding";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<Vehicle> request = new HttpEntity<>(vehicle, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        System.out.println(result);
        //Verify request succeed
       // Assert.assertEquals(201, result.getStatusCodeValue());
    }

    static public void removeVehicle()
    {
        Deque<Vehicle> deque= SpringrestoApplication.transDeque.getDeque();
        deque.removeFirst();
        Vehicle vehic=new Vehicle(1,"otobüs","4");

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:5000/VehicleRemoving";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpEntity<Vehicle> request = new HttpEntity<>(vehic);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        System.out.println(result);


    }



}
