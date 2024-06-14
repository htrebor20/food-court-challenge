package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {
    void save(Order order);
    List<Order> findByIdCustomer(Long customerId);
}
