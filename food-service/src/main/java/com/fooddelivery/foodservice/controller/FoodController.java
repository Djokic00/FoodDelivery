package com.fooddelivery.foodservice.controller;

import com.fooddelivery.foodservice.dto.request.FoodRequest;
import com.fooddelivery.foodservice.dto.response.FoodResponse;
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
    public ResponseEntity<FoodResponse> createFoodItem(@RequestBody FoodRequest foodRequest) {
        return ResponseEntity.ok(foodService.createFoodItem(foodRequest));
    }

    @GetMapping("/items")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodService.getAllFoodItems());
    }

    @GetMapping("/check-availability")
    public ResponseEntity<Boolean> checkFoodAvailability(@RequestParam Long foodItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(foodService.checkFoodAvailability(foodItemId, quantity));
    }

    @PutMapping("/increase-inventory")
    public ResponseEntity<FoodResponse> increaseFoodInventory(@RequestParam Long foodItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(foodService.increaseFoodInventory(foodItemId, quantity));
    }

    @PutMapping("/reduce-inventory")
    public ResponseEntity<FoodResponse> reduceFoodInventory(@RequestParam Long foodItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(foodService.reduceFoodInventory(foodItemId, quantity));
    }
}
