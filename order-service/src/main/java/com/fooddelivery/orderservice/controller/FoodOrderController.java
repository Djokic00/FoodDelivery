package com.fooddelivery.orderservice.controller;
import com.fooddelivery.orderservice.service.FoodOrderService;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class FoodOrderController {
    private final FoodOrderService orderService;

    public FoodOrderController(FoodOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping(path = "/getOrders")
    public ResponseEntity<OrderResponseList> getActiveOrders() {
        return ResponseEntity.ok(orderService.getActiveOrders());
    }

    @PostMapping(path = "/cancelOrder")
    public ResponseEntity<Void> cancelOrder(@RequestBody Map<String, Long> requestBody) {
        Long orderId = requestBody.get("orderId");
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}

