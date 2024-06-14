package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IOrderDishEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IOrderDishRepository;
import com.pragma.food_cout.domain.model.OrderDishRequest;
import com.pragma.food_cout.domain.spi.IOrderDishPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderDishAdapter implements IOrderDishPersistencePort {
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;
    @Override
    public OrderDishRequest save(OrderDishRequest orderDish) {
        OrderDishEntity entity = orderDishEntityMapper.toEntity(orderDish);
        OrderDishEntity save = orderDishRepository.save(entity);
        return orderDishEntityMapper.toModel(save);
    }

    @Override
    public OrderDishRequest findById(Long id) {
        Optional<OrderDishEntity> orderDishById = orderDishRepository.findById(id);
        return orderDishById.map(orderDishEntityMapper::toModel).orElse(null);
    }
}
