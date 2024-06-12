package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.utility.CustomPage;

public interface IRestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant );
    Restaurant findById(Long id);
    CustomPage<Restaurant> getAll(Integer page, Integer size);
}
