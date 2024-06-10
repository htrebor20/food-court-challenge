package com.pragma.food_cout.configuration;

import com.pragma.food_cout.adapters.driven.jpa.mysql.adapter.RestaurantAdapter;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_cout.configuration.client.IUserClient;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;

import com.pragma.food_cout.domain.api.usecase.RestaurantUseCase;

import com.pragma.food_cout.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IUserClient iUserClient;
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort () { return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper); }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), iUserClient);
    }
}
