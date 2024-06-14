package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    void save(Order order);
    List<Order> findAllByIdCustomer(Long customerId);
}
