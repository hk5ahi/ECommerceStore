package com.example.backendapplicationprogramming.server.controllers;

import com.example.backendapplicationprogramming.server.dto.CustomerDTO;
import com.example.backendapplicationprogramming.server.dto.CustomerRequestDTO;
import com.example.backendapplicationprogramming.server.entities.Customer;
import com.example.backendapplicationprogramming.server.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class customerController {

    private final CustomerService customerService;

    @Autowired
    public customerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerRequestDTO customer) {

        customerService.create(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDTO customerDTO) {
        customerService.update(id, customerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        CollectionModel<Customer> resource = CollectionModel.of(customers)
                .add(Link.of("/api/customers").withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public Optional<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);

    }



}
