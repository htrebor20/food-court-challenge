package com.pragma.food_cout.domain.api.usecase;

import com.pragma.food_cout.domain.Constants;
import com.pragma.food_cout.domain.api.ICategoryServicePort;
import com.pragma.food_cout.domain.api.IDishesServicePort;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import com.pragma.food_cout.domain.model.Category;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.domain.spi.IDishesPersistencePort;

import java.util.Optional;

public class DishesUseCase implements IDishesServicePort {
    private final IDishesPersistencePort dishesPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final ICategoryServicePort categoryServicePort;

    public DishesUseCase(IDishesPersistencePort dishesPersistencePort, IRestaurantServicePort restaurantServicePort, ICategoryServicePort categoryServicePort) {
        this.dishesPersistencePort = dishesPersistencePort;
        this.restaurantServicePort = restaurantServicePort;
        this.categoryServicePort = categoryServicePort;
    }

    @Override
    public Dishes save(Dishes dishes) {
        Restaurant restaurantById = restaurantServicePort.findById(dishes.getRestaurant().getId());
        dishes.setRestaurant(restaurantById);
        validateDishes(dishes);
        Category categoryById = categoryServicePort.findById(dishes.getCategory().getId());
        dishes.setCategory(categoryById);
        dishes.setActive(true);
        return dishesPersistencePort.saveDishes(dishes);
    }

    @Override
    public Dishes update(Dishes dishes, Long id) {
        Optional<Dishes> optionalDishes = Optional.ofNullable(dishesPersistencePort.findById(id));
        if (optionalDishes.isEmpty()) {
            throw new BadRequestValidationException(String.format(Constants.ID_VALIDATIONS_EXCEPTION_MESSAGE, dishes.getId()));
        } else {
            Dishes dishesResponse = optionalDishes.get();
            dishes.setRestaurant(dishesResponse.getRestaurant());
            validateDishes(dishes);
            dishesResponse.setActive(dishes.isActive() != null ? dishes.isActive() : dishesResponse.isActive());
            dishesResponse.setDescription(dishes.getDescription().isEmpty() ? dishesResponse.getDescription() : dishes.getDescription());
            dishesResponse.setPrice(dishes.getPrice() == null ? dishesResponse.getPrice() : dishes.getPrice());
            return dishesPersistencePort.saveDishes(dishesResponse);
        }
    }

    private void validateDishes(Dishes dishes) {
        this.validateOwnerRole(dishes);
        this.validatePrice(dishes.getPrice());
    }

    private void validateOwnerRole(Dishes dishes) {
        if (!dishes.getOwnerId().equals(dishes.getRestaurant().getOwnerId())) {
            throw new BadRequestValidationException(Constants.ID_OWNER_VALIDATIONS_EXCEPTION_MESSAGE);
        }
    }

    private void validatePrice(Integer price) {
        if (price == null || price <= 0) {
            throw new BadRequestValidationException(Constants.PRICE_VALIDATIONS_EXCEPTION_MESSAGE);
        }
    }
}
