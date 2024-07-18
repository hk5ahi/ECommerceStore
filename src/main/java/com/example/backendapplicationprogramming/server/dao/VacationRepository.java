package com.example.backendapplicationprogramming.server.dao;

import com.example.backendapplicationprogramming.server.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
