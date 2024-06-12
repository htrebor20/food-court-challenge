package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_cout.utility.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@RequiredArgsConstructor

public class RestaurantAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
        return restaurantEntityMapper.toModel(restaurantRepository.save(entity));
    }

    @Override
    public Restaurant findById(Long id) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(id);
        return restaurant.map(restaurantEntityMapper::toModel).orElse(null);
    }

    @Override
    public CustomPage<Restaurant> getAll(Integer page, Integer size) {
        Pageable pagination = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        Page<RestaurantEntity> response = restaurantRepository.findAll(pagination);
        return restaurantEntityMapper.toPage(response);
    }
}

