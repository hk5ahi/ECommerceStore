package com.example.backendapplicationprogramming.server.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String address;
    private String postal_code;
    private String firstName;
    private String lastName;
    private String phone;
    private Long division_id;
}
