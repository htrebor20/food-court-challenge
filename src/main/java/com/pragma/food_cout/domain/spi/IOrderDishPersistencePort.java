package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.OrderDishRequest;

public interface IOrderDishPersistencePort {
    OrderDishRequest save(OrderDishRequest orderDish);
    OrderDishRequest findById(Long id);
}
