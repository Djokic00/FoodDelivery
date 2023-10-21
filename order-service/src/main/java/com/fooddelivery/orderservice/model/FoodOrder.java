package com.fooddelivery.orderservice.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "food_order")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    private List<FoodItem> foodItems;

    private double totalPrice;

    private String status;
}



