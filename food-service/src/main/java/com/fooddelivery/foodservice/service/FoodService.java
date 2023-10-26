package com.fooddelivery.foodservice.service;

import com.fooddelivery.foodservice.dto.request.FoodItemRequest;
import com.fooddelivery.foodservice.dto.request.OrderRequest;
import com.fooddelivery.foodservice.dto.response.FoodItemResponse;
import com.fooddelivery.foodservice.model.FoodItem;

import java.util.List;

public interface FoodService {
    FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest);
    List<FoodItem> getAllFoodItems();
    boolean checkFoodAvailability(OrderRequest orderRequest);
    FoodItemResponse increaseFoodInventory(Long foodItemId, int quantity);
    FoodItemResponse reduceFoodInventory(Long foodItemId, int quantity);

}
