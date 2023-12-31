package com.fooddelivery.orderservice.bootstrap;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final FoodItemRepository foodItemRepository;

    public BootstrapData(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public void run(String... args) {
        FoodItem apple = new FoodItem();
        apple.setName("apple");
        apple.setQuantity(100);
        apple.setPrice(1.0);
        apple.setDeleted(false);

        FoodItem orange = new FoodItem();
        orange.setName("orange");
        orange.setQuantity(100);
        orange.setPrice(2.0);
        orange.setDeleted(false);

        FoodItem banana = new FoodItem();
        banana.setName("banana");
        banana.setQuantity(100);
        banana.setPrice(1.5);
        banana.setDeleted(false);

        FoodItem pear = new FoodItem();
        pear.setName("pear");
        pear.setQuantity(100);
        pear.setPrice(1.5);
        pear.setDeleted(false);

        foodItemRepository.save(apple);
        foodItemRepository.save(orange);
        foodItemRepository.save(banana);
        foodItemRepository.save(pear);
    }
}

