package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor

public class RestaurantAdapter implements IRestaurantPersistencePort {

private final IRestaurantRepository restaurantRepository;
private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
        restaurantRepository.save(entity);
    }

    @Override
    public Restaurant findById(Long id) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(id);
        return restaurant.map(restaurantEntityMapper::toModel).orElse(null);
    }
}
