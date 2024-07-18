package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.dao.CustomerRepository;
import com.example.backendapplicationprogramming.server.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class CustomerDataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDataInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer createCustomer(String firstName, String lastName, String address, String postalCode,String phone, Date createDate, Date lastUpdate) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setPostal_code(postalCode);
        customer.setPhone(phone);
        customer.setCreate_date(createDate);
        customer.setLast_update(lastUpdate);
        return customer;
    }
    @Override
    public void run(String... args) {
        // Check if data already exists
        if (customerRepository.count() == 1) {
            // Add sample customers
            addSampleCustomers();
        }
    }

    private void addSampleCustomers() {
        // Create dates
        Date createDate = new Date();
        Date lastUpdate = new Date();

        Customer sampleCustomer1 = createCustomer("Edgar", "Poe", "1234 Elm Street", "12345","22551511", createDate, lastUpdate);
        Customer sampleCustomer2 = createCustomer("Herman", "Melville", "5678 Oak Street", "67890","99951511", createDate, lastUpdate);
        Customer sampleCustomer3 = createCustomer("Mark", "Twain", "91011 Pine Street", "91011", "2786111",createDate, lastUpdate);
        Customer sampleCustomer4 = createCustomer("Emily", "Dickinson", "121314 Birch Street", "121314","33421587", createDate, lastUpdate);
        Customer sampleCustomer5 = createCustomer("Walt", "Whitman", "151617 Maple Street", "151617","20047810", createDate, lastUpdate);

        // Create a list of customers
        List<Customer> customers = Arrays.asList(sampleCustomer1, sampleCustomer2, sampleCustomer3, sampleCustomer4, sampleCustomer5);
        for(Customer customer: customers){
            if(customerRepository.findCustomerByFirstNameAndLastName(customer.getFirstName(), customer.getLastName()).isEmpty()){
                customerRepository.save(customer);
            }
        }
    }
}
