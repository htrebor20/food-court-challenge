package com.pragma.food_cout.domain.api.usecase;

import com.pragma.food_cout.domain.Constants;
import com.pragma.food_cout.domain.api.IOrderDishesServicePort;
import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import com.pragma.food_cout.domain.model.OrderDishRequest;
import com.pragma.food_cout.domain.spi.IOrderDishPersistencePort;

import java.util.Optional;

public class OrderDishUseCase implements IOrderDishesServicePort {
    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }

    @Override
    public OrderDishRequest save(OrderDishRequest orderDish) {
       return orderDishPersistencePort.save(orderDish);
    }

    @Override
    public OrderDishRequest findById(Long id) {
        Optional<OrderDishRequest> orderDish = Optional.ofNullable(orderDishPersistencePort.findById(id));
        if (orderDish.isEmpty()) {
            throw new BadRequestValidationException(String.format(Constants.ID_VALIDATIONS_EXCEPTION_MESSAGE, id));
        }
        return null;
    }
}

