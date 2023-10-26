package com.fooddelivery.orderservice.controller;

import com.fooddelivery.orderservice.dto.request.FoodOrderRequest;
import com.fooddelivery.orderservice.dto.response.FoodOrderResponse;
import com.fooddelivery.orderservice.service.FoodOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class FoodOrderController {
    private final FoodOrderService orderService;

    public FoodOrderController(FoodOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<FoodOrderResponse> createOrder(@RequestBody FoodOrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }
}

