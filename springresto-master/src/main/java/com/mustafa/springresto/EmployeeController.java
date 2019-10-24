package com.mustafa.springresto;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.Resources;

@RestController
class EmployeeController {

    private final EmployeeRepository repository;



    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;

    }



    @GetMapping("/employees")
    ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {

        List<EntityModel<Employee>> employees = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(employee -> new EntityModel<>(employee, //
                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel(), //
                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"))) //
                .collect(Collectors.toList());

        return ResponseEntity.ok( //
                new CollectionModel<>(employees, //
                        linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee employee) {

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

    /**
     * Look up a single {@link Employee} and transform it into a REST resource. Then return it through Spring Web's
     * {@link ResponseEntity} fluent API.
     *
     * @param id
     */
    @GetMapping("/employees/{id}")
    ResponseEntity<EntityModel<Employee>> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(employee -> new EntityModel<>(employee, //
                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel(), //
                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"))) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update existing employee then return a Location header.
     *
     * @param employee
     * @param id
     * @return
     */
    @PutMapping("/employees/{id}")
    ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

        Employee employeeToUpdate = employee;
        employeeToUpdate.setId(id);
        repository.save(employeeToUpdate);

        Link newlyCreatedLink = linkTo(methodOn(EmployeeController.class).findOne(id)).withSelfRel();

        try {
            return ResponseEntity.noContent().location(new URI(newlyCreatedLink.getHref())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to update " + employeeToUpdate);
        }
    }

}
