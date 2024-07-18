package com.example.backendapplicationprogramming.server.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseResponse {
    private String orderTrackingNumber;
}
