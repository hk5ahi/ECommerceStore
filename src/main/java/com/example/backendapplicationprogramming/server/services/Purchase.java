package com.example.backendapplicationprogramming.server.services;

import com.example.backendapplicationprogramming.server.entities.Cart;
import com.example.backendapplicationprogramming.server.entities.CartItem;
import com.example.backendapplicationprogramming.server.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
@AllArgsConstructor
@Data
public class Purchase {
    private Cart cart;
    private Set<CartItem> cartItems;
    private Customer customer;
}
