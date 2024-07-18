package com.example.backendapplicationprogramming.server.dto;

import com.example.backendapplicationprogramming.server.entities.Links;
import jakarta.persistence.Transient;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
public class VacationDTO{

    private Long id;

    private String vacation_title;

    private String description;

    private BigDecimal travel_price;

    private String image_URL;

    private Date create_date;

    private Date last_update;

    private Set<ExcursionDTO> excursions = new HashSet<>();

    @Transient
    private Links _links;
    public VacationDTO() {

    }

}
