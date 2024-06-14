package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IOrderRepository;
import com.pragma.food_cout.configuration.client.IUserClient;
import com.pragma.food_cout.domain.Constants;
import com.pragma.food_cout.domain.api.IDishesServicePort;
import com.pragma.food_cout.domain.api.IOrderDishesServicePort;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import com.pragma.food_cout.domain.model.*;
import com.pragma.food_cout.domain.spi.IOrderPersistencePort;
import com.pragma.food_cout.utility.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private final IDishesServicePort dishesServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IUserClient userClient;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IOrderDishesServicePort orderDishesServicePort;

    @Override
    public void save(Order order) {
        validateCustomerOrder(order.getIdCustomer());
        Restaurant restaurant = restaurantServicePort.findById(order.getIdRestaurant());
        validateDishesByRestaurant(order.getDishes(), order.getIdRestaurant());
        order.setOrderDate(new Date(System.currentTimeMillis()).toLocalDate());
        order.setStatus(OrderStatusEnum.PENDING);

        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        orderEntity.setRestaurant(restaurantEntityMapper.toEntity(restaurant));
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        Order orderModel = orderEntityMapper.toModel(savedOrder);

        for (OrderDish localOrder : order.getDishes()) {
            Dishes localDish = dishesServicePort.findById(localOrder.getDishId());
            OrderDishRequest orderDishRequest = new OrderDishRequest(null, orderModel, localDish, localOrder.getQuantity());
            orderDishesServicePort.save(orderDishRequest);
        }
    }

    @Override
    public List<Order> findAllByIdCustomer(Long customerId) {
        List<OrderEntity> byIdCustomer = orderRepository.findByIdCustomer(customerId);
        return orderEntityMapper.toOrderList(byIdCustomer);
    }

    public void validateDishesByRestaurant(List<OrderDish> dishes, Long restaurantId) {
        List<Long> ids = dishes.stream().map(OrderDish::getDishId).toList();
        List<Dishes> allDishesByIds = dishesServicePort.findAllByIds(ids);
        boolean allMatch = allDishesByIds.stream().allMatch(dish -> dish.getRestaurant().getId().equals(restaurantId));
        if (!allMatch) {
            throw new BadRequestValidationException(Constants.DISHES_RESTAURANT_MATCH_EXCEPTION_MESSAGE);
        }
    }

    public void validateCustomerOrder( Long customerId) {
        List<Order> byIdCustomer = findAllByIdCustomer(customerId);
        boolean allMatch = byIdCustomer.stream().allMatch(order -> order.getStatus() == OrderStatusEnum.COMPLETED);
        if (!allMatch) {
            throw new BadRequestValidationException(Constants.CUSTOMER_ACTIVE_ORDER_EXCEPTION_MESSAGE);
        }
    }
}
