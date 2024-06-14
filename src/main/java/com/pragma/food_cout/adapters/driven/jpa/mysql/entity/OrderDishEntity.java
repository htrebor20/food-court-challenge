package com.pragma.food_cout.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_dish")
public class OrderDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private DishesEntity dish;

    private int quantity;

    public OrderDishEntity(OrderEntity order, DishesEntity dish, int quantity) {
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
    }
}
