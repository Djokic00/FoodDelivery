package com.fooddelivery.orderservice.repository;

import com.fooddelivery.orderservice.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Optional<FoodItem> findByName(String name);
    Optional<FoodItem> findByNameAndQuantityGreaterThanEqual(String name, int quantity);
    List<FoodItem> findByNameInAndDeletedFalse(List<String> foodItemNames);
    Optional<FoodItem> findByNameAndDeletedFalse(String name);
}
