package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.dao.*;

import com.example.backendapplicationprogramming.server.entities.*;


import com.example.backendapplicationprogramming.server.services.CheckoutService;
import com.example.backendapplicationprogramming.server.services.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl( CartRepository cartRepository, CustomerRepository customerRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Purchase processPurchase(Purchase purchaseDto) {
        // Convert DTO to Entities
        Customer customer = purchaseDto.getCustomer();
        customer.setCreate_date(new Date());
        customer.setLast_update(new Date());
        Cart cart = purchaseDto.getCart();
        if(cart.getCreate_date()==null){
            cart.setCreate_date(new Date());
        }
        cart.setLast_update(new Date());
        Set<CartItem> cartItems = purchaseDto.getCartItems();

        customer = customerRepository.save(customer);
        // Set unique order tracking number
        cart.setOrderTrackingNumber(generateOrderTrackingNumber());
        // Save cart
        cart.setCustomer(customer);
        cart.setStatus(StatusType.ordered);
        cart = cartRepository.save(cart);

        // Set cart for each cart item and save them
        for (CartItem cartItem : cartItems) {
            cartItem.setCart(cart);
            cartItem.setCreate_date(new Date());
            cartItem.setLast_update(new Date());
            cartItemRepository.save(cartItem);
        }

        return purchaseDto;
    }

    private static String generateOrderTrackingNumber() {
        long timestamp = Instant.now().getEpochSecond() % 1000000000L; // Last 9 digits of current time in seconds
        Random random = new Random();
        int randomDigit = random.nextInt(10); // Generate a single random digit to make it 10 digits
        long orderTrackingNumber = timestamp * 10 + randomDigit; // Combine them
        return String.valueOf(orderTrackingNumber);
    }

}
