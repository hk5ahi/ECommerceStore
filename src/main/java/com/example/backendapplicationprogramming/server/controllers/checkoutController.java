package com.example.backendapplicationprogramming.server.controllers;

import com.example.backendapplicationprogramming.server.services.CheckoutService;
import com.example.backendapplicationprogramming.server.services.Purchase;
import com.example.backendapplicationprogramming.server.services.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class checkoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public checkoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> handlePurchase(@RequestBody Purchase purchaseDto) {

        Purchase purchase = checkoutService.processPurchase(purchaseDto);
        PurchaseResponse response = new PurchaseResponse(purchase.getCart().getOrderTrackingNumber());
        return ResponseEntity.ok(response);
    }
}
