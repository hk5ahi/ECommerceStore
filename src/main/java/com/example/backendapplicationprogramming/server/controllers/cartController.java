package com.example.backendapplicationprogramming.server.controllers;

import com.example.backendapplicationprogramming.server.entities.Cart;
import com.example.backendapplicationprogramming.server.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class cartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<CollectionModel<Cart>> getAllCarts() {
        List<Cart> vacations = cartService.getAllCarts();
        CollectionModel<Cart> resource = CollectionModel.of(vacations)
                .add(Link.of("/api/carts").withSelfRel());
        return ResponseEntity.ok(resource);
    }
}
