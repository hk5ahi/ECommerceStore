package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.dto.ExcursionDTO;
import com.example.backendapplicationprogramming.server.entities.Excursion;

import java.util.List;
import java.util.Optional;


public interface ExcursionService {


    Optional<ExcursionDTO> getExcursionByIdAndVacationId(Long vacationId, Long excursionId);
    List<Excursion> getAllExcursionsByVacationId(Long vacationId);

}
