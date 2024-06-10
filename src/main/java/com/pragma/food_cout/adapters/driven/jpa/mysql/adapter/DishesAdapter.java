package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IDishesEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IDishesRepository;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.domain.spi.IDishesPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor

public class DishesAdapter implements IDishesPersistencePort {
    private final IDishesRepository dishesRepository;
    private final IDishesEntityMapper dishesEntityMapper;
    @Override
    public Dishes saveDishes(Dishes dishes) {
        DishesEntity entity = dishesEntityMapper.toEntity(dishes);
        return dishesEntityMapper.toModel(dishesRepository.save(entity));
    }

    @Override
    public Dishes findById(Dishes dishes) {
        DishesEntity entity = dishesEntityMapper.toEntity(dishes);
        Optional<DishesEntity> optionalDishes = dishesRepository.findById(entity.getId());
        return optionalDishes.map(dishesEntityMapper::toModel).orElse(null);
    }
}
