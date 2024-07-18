package com.example.backendapplicationprogramming.server.controllers;


import com.example.backendapplicationprogramming.server.services.CountryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/countries")
public class countryController {

    private final CountryService countryService;

    public countryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<?> getCountries() {
        Map<String, Object> response = countryService.getAllCountries();
        return ResponseEntity.ok(response);

    }

}
