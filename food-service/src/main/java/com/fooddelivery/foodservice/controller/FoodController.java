package com.fooddelivery.foodservice.controller;

import com.fooddelivery.foodservice.dto.request.FoodItemRequest;
import com.fooddelivery.foodservice.dto.request.OrderRequest;
import com.fooddelivery.foodservice.dto.response.FoodItemResponse;
import com.fooddelivery.foodservice.model.FoodItem;
import com.fooddelivery.foodservice.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/create")
    public ResponseEntity<FoodItemResponse> createFoodItem(@RequestBody FoodItemRequest foodItemRequest) {
        return ResponseEntity.ok(foodService.createFoodItem(foodItemRequest));
    }

    @GetMapping("/items")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodService.getAllFoodItems());
    }

    @PostMapping("/check-availability")
    public ResponseEntity<Boolean> checkFoodAvailability(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(foodService.checkFoodAvailability(orderRequest));
    }

    @PutMapping("/increase-inventory")
    public ResponseEntity<FoodItemResponse> increaseFoodInventory(@RequestParam Long foodItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(foodService.increaseFoodInventory(foodItemId, quantity));
    }

    @PutMapping("/reduce-inventory")
    public ResponseEntity<FoodItemResponse> reduceFoodInventory(@RequestParam Long foodItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(foodService.reduceFoodInventory(foodItemId, quantity));
    }
}
