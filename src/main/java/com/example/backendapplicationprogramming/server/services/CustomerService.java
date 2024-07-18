package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.dto.CustomerDTO;
import com.example.backendapplicationprogramming.server.dto.CustomerRequestDTO;
import com.example.backendapplicationprogramming.server.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
     void create(CustomerRequestDTO customer);
     List<Customer> getAllCustomers();
     Optional<CustomerDTO> getCustomerById(Long id);
     void update(Long id, CustomerRequestDTO customer);
}
