package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.entities.Country;

import java.util.Map;

public interface CountryService {

    Map<String, Object> getAllCountries();

    Country getCountryById(String country_URL);


}
