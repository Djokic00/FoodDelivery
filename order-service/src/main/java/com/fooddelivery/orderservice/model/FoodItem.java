package com.fooddelivery.orderservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int quantity;
    private boolean deleted;

    @ManyToMany(mappedBy = "foodItems")
    private List<FoodOrder> foodOrders;

    public FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

