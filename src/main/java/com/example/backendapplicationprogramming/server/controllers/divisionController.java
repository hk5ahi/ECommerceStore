package com.example.backendapplicationprogramming.server.controllers;


import com.example.backendapplicationprogramming.server.services.DivisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;


@RestController
@RequestMapping("/api/divisions")
public class divisionController {

    private final DivisionService divisionService;

    public divisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping
    public ResponseEntity<?> getDivisions() {
        Map<String, Object> response = divisionService.getAllDivisions();
        return ResponseEntity.ok(response);
    }
}
