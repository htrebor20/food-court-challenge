package com.pragma.food_cout.domain.api.usecase;

import com.pragma.food_cout.domain.api.IOrderServicePort;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.domain.spi.IOrderPersistencePort;
import com.pragma.food_cout.utility.CustomPage;

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

    @Override
    public CustomPage<OrderWithDishes> getAllByStatus(Integer page, Integer size, String status) {
        return orderPersistencePort.getAllByStatus(page, size, status);
    }

    @Override
    public void assignEmployee(Long idEmployee, Long id) {
       orderPersistencePort.update(idEmployee, id);
    }

    @Override
    public void updateOrderState(Long id) {
        orderPersistencePort.updateOrder(id);
    }
}
