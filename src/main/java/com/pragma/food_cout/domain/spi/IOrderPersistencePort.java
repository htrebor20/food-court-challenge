package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.utility.CustomPage;

import java.util.List;

public interface IOrderPersistencePort {
    void save(Order order);

    void update(Long idEmployee, Long id);

    List<Order> findAllByIdCustomer(Long customerId);

    OrderEntity findById(Long id);

    CustomPage<OrderWithDishes> getAllByStatus(Integer page, Integer size, String status);

    void updateOrder(Long id);
}
