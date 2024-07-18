package com.example.backendapplicationprogramming.server.dao;

import com.example.backendapplicationprogramming.server.entities.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    Optional<Excursion> findByIdAndVacationId(Long id, Long vacationId);
    List<Excursion> findByVacationId(Long vacationId);
}
