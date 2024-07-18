package com.example.backendapplicationprogramming.server.dao;

import com.example.backendapplicationprogramming.server.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByFirstNameAndLastName(String firstName, String lastName);
}
