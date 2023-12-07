package com.fooddelivery.orderservice.repository;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Optional<FoodItem> findByName(String name);
    Optional<FoodItem> findByNameAndQuantityGreaterThanEqual(String name, int quantity);
    List<FoodItem> findByNameInAndDeletedFalse(List<String> foodItemNames);
    Optional<FoodItem> findByNameAndDeletedFalse(String name);

    @Query("SELECT new com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse(fi.name, fi.price) FROM FoodItem fi WHERE fi.id = :id")
    Optional<FoodItemResponse> findFoodItemDetailsById(@Param("id") Long id);
}
