package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.utility.CustomPage;

import java.util.List;

public interface IOrderPersistencePort {
    void save(Order order);
    List<Order> findAllByIdCustomer(Long customerId);
    CustomPage<OrderWithDishes> getAllByStatus(Integer page, Integer size, String status);
}
