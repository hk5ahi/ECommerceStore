package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.dao.CustomerRepository;
import com.example.backendapplicationprogramming.server.dto.CustomerDTO;
import com.example.backendapplicationprogramming.server.dto.CustomerRequestDTO;
import com.example.backendapplicationprogramming.server.entities.Country;
import com.example.backendapplicationprogramming.server.entities.Customer;
import com.example.backendapplicationprogramming.server.entities.Division;
import com.example.backendapplicationprogramming.server.services.CountryService;
import com.example.backendapplicationprogramming.server.services.CustomerService;
import com.example.backendapplicationprogramming.server.services.DivisionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    private DivisionService divisionService;
    @Autowired
    private CountryService countryService;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public void create(CustomerRequestDTO customer) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setPostal_code(customer.getPostal_code());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setCreate_date(new Date());
        newCustomer.setLast_update(new Date());

        if (customer.getDivision() != null) {
            try {
                Division division = divisionService.getDivisionById(customer.getDivision());
                if (division != null) {
                    if (customer.getCountry() != null) {
                        Country country = countryService.getCountryById(customer.getCountry());
                        if (country != null) {
                            division.setCountry(country);
                        }
                    }
                    newCustomer.setDivision(division);
                }
            } catch (RestClientException ex) {
                throw new RuntimeException("Error retrieving division or country information", ex);
            }
        }
        customerRepository.save(newCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(Long id) {
        Optional<Customer> customer= customerRepository.findById(id);
        return customer.map(this::convertToDto);
    }

    @Override
    public void update(Long id,CustomerRequestDTO customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setPostal_code(customer.getPostal_code());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setLast_update(new Date());
            if (customer.getDivision() != null) {
                Division division = divisionService.getDivisionById( customer.getDivision());
                if (division != null) {
                    updatedCustomer.setDivision(division);
                }
            }
            customerRepository.save(updatedCustomer);
        }
    }
    private CustomerDTO convertToDto(Customer customer) {
        // Perform mapping from Customer entity to CustomerDTO
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPostal_code(customer.getPostal_code());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPhone(customer.getPhone());
        // Check if division is not null before accessing its properties
        if (customer.getDivision() != null) {
            customerDTO.setDivision_id(customer.getDivision().getId());
        }
        return customerDTO;
    }
}
