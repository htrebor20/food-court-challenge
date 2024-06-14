package com.pragma.food_cout.configuration;

import com.pragma.food_cout.adapters.driven.jpa.mysql.adapter.*;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.*;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.*;
import com.pragma.food_cout.configuration.client.IUserClient;
import com.pragma.food_cout.domain.api.*;
import com.pragma.food_cout.domain.api.usecase.*;
import com.pragma.food_cout.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IDishesEntityMapper dishesEntityMapper;
    private final IDishesRepository dishesRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final IUserClient userClient;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userClient);
    }

    @Bean
    public IDishesPersistencePort dishesPersistencePort() {
        return new DishesAdapter(dishesRepository, dishesEntityMapper);
    }

    @Bean
    public IDishesServicePort dishesServicePort() {
        return new DishesUseCase(dishesPersistencePort(), restaurantServicePort(), categoryServicePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderAdapter(dishesServicePort(), restaurantServicePort(), userClient, orderRepository, orderEntityMapper, restaurantEntityMapper,  ordersDishesServicePort());
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort() {
        return new OrderDishAdapter(orderDishRepository, orderDishEntityMapper);
    }

    @Bean
    public IOrderDishesServicePort ordersDishesServicePort () {
        return new OrderDishUseCase(orderDishPersistencePort());
    }
}
