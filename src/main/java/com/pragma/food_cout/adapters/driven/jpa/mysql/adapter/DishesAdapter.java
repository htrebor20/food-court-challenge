package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IDishesEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IDishesRepository;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.domain.spi.IDishesPersistencePort;
import com.pragma.food_cout.utility.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public Dishes findById(Long id) {
        Optional<DishesEntity> optionalDishes = dishesRepository.findById(id);
        return optionalDishes.map(dishesEntityMapper::toModel).orElse(null);
    }

    @Override
    public CustomPage<Dishes> getAll(Integer page, Integer size, Long categoryId) {
        Pageable pagination = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        Page<DishesEntity> response = categoryId != null ?
                dishesRepository.findByCategory_Id(categoryId, pagination) :
                dishesRepository.findAll(pagination);
        return dishesEntityMapper.toPage(response);
    }
}
