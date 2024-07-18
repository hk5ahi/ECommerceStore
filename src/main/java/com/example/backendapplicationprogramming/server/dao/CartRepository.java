package com.example.backendapplicationprogramming.server.dao;

import com.example.backendapplicationprogramming.server.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
