package com.pragma.food_cout.configuration;

import com.pragma.food_cout.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.pragma.food_cout.adapters.driven.jpa.mysql.adapter.DishesAdapter;
import com.pragma.food_cout.adapters.driven.jpa.mysql.adapter.RestaurantAdapter;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IDishesEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IDishesRepository;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_cout.configuration.client.IUserClient;
import com.pragma.food_cout.configuration.security.jwt.JwtTokenUtil;
import com.pragma.food_cout.domain.api.ICategoryServicePort;
import com.pragma.food_cout.domain.api.IDishesServicePort;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.api.usecase.CategoryUseCase;
import com.pragma.food_cout.domain.api.usecase.DishesUseCase;
import com.pragma.food_cout.domain.api.usecase.RestaurantUseCase;
import com.pragma.food_cout.domain.spi.ICategoryPersistencePort;
import com.pragma.food_cout.domain.spi.IDishesPersistencePort;
import com.pragma.food_cout.domain.spi.IRestaurantPersistencePort;
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
    private final IUserClient iUserClient;
    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort () { return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper); }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), iUserClient);
    }

    @Bean
    public IDishesPersistencePort  dishesPersistencePort () { return new DishesAdapter(dishesRepository, dishesEntityMapper); }

    @Bean
    public IDishesServicePort  dishesServicePort() {
        return new DishesUseCase(dishesPersistencePort(), restaurantServicePort(), categoryServicePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort () { return new CategoryAdapter(categoryRepository,categoryEntityMapper); }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
