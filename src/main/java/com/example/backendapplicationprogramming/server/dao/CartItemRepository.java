package com.example.backendapplicationprogramming.server.dao;

import com.example.backendapplicationprogramming.server.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
