package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.entities.Division;

import java.util.Map;

public interface DivisionService {

    Map<String, Object> getAllDivisions();
    Division getDivisionById(String division_URL);
}
