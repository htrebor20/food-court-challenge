package com.pragma.food_cout.domain.api.usecase;

import com.pragma.food_cout.domain.api.IOrderServicePort;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void save(Order order) {
        orderPersistencePort.save(order);
    }

    @Override
    public List<Order> findByIdCustomer(Long customerId) {
        return orderPersistencePort.findAllByIdCustomer(customerId);
    }
}
