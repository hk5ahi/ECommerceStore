package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.dao.CartRepository;
import com.example.backendapplicationprogramming.server.entities.Cart;
import com.example.backendapplicationprogramming.server.services.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
