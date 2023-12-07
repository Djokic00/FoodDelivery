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
@Table(name = "food_item")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int quantity;
    private boolean deleted;

    @OneToMany(mappedBy = "foodItem", cascade = CascadeType.ALL)
    private List<FoodOrderItem> foodOrderItems;
}

