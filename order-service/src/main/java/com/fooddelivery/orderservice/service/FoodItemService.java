package com.fooddelivery.orderservice.service;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;

import java.util.List;

public interface FoodItemService {
    FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest);
    List<FoodItem> getAllFoodItems();
    boolean checkFoodAvailability(OrderRequest orderRequest);
    FoodItemResponse increaseFoodInventory(Long foodItemId, int quantity);
    List<FoodItemResponse> reduceFoodInventory(OrderResponse orderResponse);

}