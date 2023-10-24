package com.fooddelivery.foodservice.service;

import com.fooddelivery.foodservice.dto.request.FoodRequest;
import com.fooddelivery.foodservice.dto.response.FoodResponse;
import com.fooddelivery.foodservice.model.FoodItem;

import java.util.List;

public interface FoodService {
    FoodResponse createFoodItem(FoodRequest foodRequest);
    List<FoodItem> getAllFoodItems();
    boolean checkFoodAvailability(Long foodItemId, int quantity);
    FoodResponse increaseFoodInventory(Long foodItemId, int quantity);
    FoodResponse reduceFoodInventory(Long foodItemId, int quantity);

}
