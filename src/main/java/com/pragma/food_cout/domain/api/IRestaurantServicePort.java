package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant );
    Restaurant findById(Long id);
}
