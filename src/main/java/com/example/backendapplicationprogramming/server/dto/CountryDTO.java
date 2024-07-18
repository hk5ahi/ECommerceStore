package com.example.backendapplicationprogramming.server.dto;

import com.example.backendapplicationprogramming.server.entities.Links;
import lombok.Data;

@Data
public class CountryDTO {

    private Long id;
    private String country_name;
    private Links _links;
}
