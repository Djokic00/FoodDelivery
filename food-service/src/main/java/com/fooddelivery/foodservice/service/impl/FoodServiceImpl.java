package com.fooddelivery.foodservice.service.impl;

import com.fooddelivery.foodservice.dto.request.FoodItemRequest;
import com.fooddelivery.foodservice.dto.request.OrderRequest;
import com.fooddelivery.foodservice.dto.response.FoodItemResponse;
import com.fooddelivery.foodservice.exception.NotFoundException;
import com.fooddelivery.foodservice.mapper.FoodMapper;
import com.fooddelivery.foodservice.model.FoodItem;
import com.fooddelivery.foodservice.repository.FoodItemRepository;
import com.fooddelivery.foodservice.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {
    private final FoodItemRepository foodItemRepository;
    private final FoodMapper foodMapper;

    public FoodServiceImpl(FoodItemRepository foodItemRepository, FoodMapper foodMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodMapper = foodMapper;
    }

    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest) {
        FoodItem foodItem = foodItemRepository.save(foodMapper.requestToModel(foodItemRequest));
        return foodMapper.modelToResponse(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public boolean checkFoodAvailability(OrderRequest orderRequest) {
        List<FoodItemRequest> foodItems = orderRequest.getFoodItems();

        for (FoodItemRequest foodItemRequest : foodItems) {
            String name = foodItemRequest.getName();
            int quantity = foodItemRequest.getQuantity();

            boolean isAvailable = checkSingleFoodItemAvailability(name, quantity);

            if (!isAvailable) {
                return false;
            }
        }
        return true;
    }


    public boolean checkSingleFoodItemAvailability(String name, int quantity) {
        Optional<FoodItem> foodItem = foodItemRepository.findByNameAndQuantityGreaterThanEqual(name, quantity);
        if (foodItem.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public FoodItemResponse increaseFoodInventory(Long foodItemId, int quantity) {
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(() -> {
            log.error("Food item with given id does not exists '{}'", foodItemId);
            throw new NotFoundException("Food item with given id does not exists!");
        });
        if (foodItem != null) {
            int newQuantity = foodItem.getQuantity() + quantity;
            foodItem.setQuantity(newQuantity);
            foodItemRepository.save(foodItem);
        }
        return foodMapper.modelToResponse(foodItem);
    }

    @Override
    public FoodItemResponse reduceFoodInventory(Long foodItemId, int quantity) {
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(() -> {
            log.error("Food item with given id does not exists '{}'", foodItemId);
            throw new NotFoundException("Food item with given id does not exists!");
        });
        if (foodItem != null) {
            int currentQuantity = foodItem.getQuantity();
            if (currentQuantity >= quantity) {
                foodItem.setQuantity(currentQuantity - quantity);
                foodItemRepository.save(foodItem);
            }
        }
        return foodMapper.modelToResponse(foodItem);
    }
}
