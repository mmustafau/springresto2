package com.mustafa.springresto;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Deque;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class VehicleController {

    Deque<Vehicle> deque= SpringrestoApplication.transDeque.getDeque();

 //   Vehicle vehicle=new Vehicle();
  //  Deque<Vehicle> deque= SpringrestoApplication.transDeque.getDeque();


    @RequestMapping(value = "/VehicleAdding")
    ResponseEntity<?> newVehicle(@RequestBody Vehicle newVehicle) throws URISyntaxException {

       deque.addFirst(newVehicle);





        return new ResponseEntity<Vehicle>(newVehicle, HttpStatus.OK);
    }


    @RequestMapping(value = "/VehicleRemoving")
    ResponseEntity<?> bos(@RequestBody Vehicle newVehicle) throws URISyntaxException {

        deque.removeFirst();


        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:5000/VehicleRemoving";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<Vehicle> request = new HttpEntity<>(newVehicle, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);


        return new ResponseEntity<Vehicle>(newVehicle, HttpStatus.OK);
    }


/*
    @PostMapping("/VehicleAdding")
    ResponseEntity<?> newVehicle(@RequestBody Vehicle vehicle) {

        try {



            Employee savedEmployee = repository.save(employee);

            EntityModel<Employee> employeeResource = new EntityModel<>(savedEmployee, //
                    linkTo(methodOn(EmployeeController.class).findOne(savedEmployee.getId())).withSelfRel());

            return ResponseEntity //
                    .created(new URI(employeeResource.getRequiredLink(IanaLinkRelations.SELF).getHref())) //
                    .body(employeeResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create " + employee);
        }



    }
 */






}
