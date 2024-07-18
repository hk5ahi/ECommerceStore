package com.example.backendapplicationprogramming.server.dto;

import com.example.backendapplicationprogramming.server.entities.Links;
import lombok.Data;

@Data
public class DivisionDTO {

    private Long id;
    private String division_name;
    private Long country_id;
    private Links _links;

}
