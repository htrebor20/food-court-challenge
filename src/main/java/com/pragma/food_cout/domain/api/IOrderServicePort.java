package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.utility.CustomPage;

import java.util.List;

public interface IOrderServicePort {
    void save(Order order);
    List<Order> findByIdCustomer(Long customerId);
    CustomPage<OrderWithDishes> getAllByStatus(Integer page, Integer size, String status);
}
