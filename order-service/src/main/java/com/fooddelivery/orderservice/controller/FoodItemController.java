package com.fooddelivery.orderservice.controller;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.service.FoodItemService;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemQuantityResponse;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<FoodItemResponse> createFoodItem(@RequestBody FoodItemRequest foodItemRequest) {
        return ResponseEntity.ok(foodItemService.createFoodItem(foodItemRequest));
    }

    @GetMapping("/items")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @PostMapping("/check-availability")
    public ResponseEntity<Boolean> checkFoodAvailability(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(foodItemService.checkFoodAvailability(orderRequest));
    }

    @PutMapping("/increase-inventory")
    public ResponseEntity<List<FoodItemResponse>> increaseFoodInventory(@RequestBody List<FoodItemQuantityResponse> foodItemQuantityResponseList) {
        return ResponseEntity.ok(foodItemService.increaseFoodInventory(foodItemQuantityResponseList));
    }

    @PutMapping("/reduce-inventory")
    public ResponseEntity<List<FoodItemResponse>> reduceFoodInventory(@RequestBody List<FoodItemResponse> foodItemResponseList) {
        return ResponseEntity.ok(foodItemService.reduceFoodInventory(foodItemResponseList));
    }
}

