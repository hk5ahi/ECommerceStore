package com.example.backendapplicationprogramming.server.dto;

import lombok.Data;

@Data
public class CustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String postal_code;
    private String phone;
    private String country;
    private String division;
}
