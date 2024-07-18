package com.example.backendapplicationprogramming.server.dao;


import com.example.backendapplicationprogramming.server.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
}
