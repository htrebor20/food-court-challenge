package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.OrderDishRequest;

public interface IOrderDishesServicePort {
    OrderDishRequest save(OrderDishRequest orderDish);
    OrderDishRequest findById(Long id);
}
