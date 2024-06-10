package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant );
    Restaurant findById(Long id);
}
