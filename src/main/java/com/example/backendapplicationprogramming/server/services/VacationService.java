package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.dto.VacationDTO;
import com.example.backendapplicationprogramming.server.entities.Vacation;

import java.util.List;
import java.util.Optional;

public interface VacationService {

    Optional<VacationDTO> getVacationById(Long id);
    List<Vacation> getAllVacations();
    Optional<String> getVacationTitleById(Long id);

}
