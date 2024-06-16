package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.IOrderRepository;
import com.pragma.food_cout.configuration.client.IMessagingClient;
import com.pragma.food_cout.configuration.client.IUserClient;
import com.pragma.food_cout.domain.Constants;
import com.pragma.food_cout.domain.api.IDishesServicePort;
import com.pragma.food_cout.domain.api.IOrderDishesServicePort;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.enums.RoleEnum;
import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import com.pragma.food_cout.domain.model.*;
import com.pragma.food_cout.domain.spi.IOrderPersistencePort;
import com.pragma.food_cout.utility.CustomPage;
import com.pragma.food_cout.utility.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private final IDishesServicePort dishesServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IOrderDishesServicePort orderDishesServicePort;
    private final IUserClient userClient;
    private final IMessagingClient messagingClient;

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
    public void update(Long idEmployee, Long id) {
        OrderEntity orderEntity = findById(id);
        orderEntity.setAssignedEmployee(idEmployee);
        orderEntity.setStatus(OrderStatusEnum.IN_PREPARATION);
        orderRepository.save(orderEntity);
    }

    @Override
    public void updateOrder(Long id) {
        OrderEntity orderEntity = findById(id);
        orderEntity.setStatus(createState(orderEntity));
        String deliveryCode = String.format("%06d", (int) (Math.random() * 1000000));
        orderEntity.setDeliveryCode(deliveryCode);
        OrderEntity updatedOrder = orderRepository.save(orderEntity);
        sendSMS(updatedOrder, deliveryCode);
    }

    @Override
    public List<Order> findAllByIdCustomer(Long customerId) {
        List<OrderEntity> byIdCustomer = orderRepository.findByIdCustomer(customerId);
        return orderEntityMapper.toOrderList(byIdCustomer);
    }

    @Override
    public OrderEntity findById(Long id) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);

        if (optionalOrderEntity.isEmpty()) {
            throw new BadRequestValidationException(String.format(Constants.ID_FIELD_VALIDATIONS_EXCEPTION_MESSAGE, id, "order"));
        }
        return optionalOrderEntity.get();
    }

    @Override
    public CustomPage<OrderWithDishes> getAllByStatus(Integer page, Integer size, String status) {
        Pageable pagination = PageRequest.of(page, size);
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(status.toUpperCase());
        Page<OrderEntity> response = orderRepository.findByStatus(orderStatusEnum, pagination);
        return orderEntityMapper.toPageOrderWithDishes(response);
    }


    public void validateDishesByRestaurant(List<OrderDish> dishes, Long restaurantId) {
        List<Long> ids = dishes.stream().map(OrderDish::getDishId).toList();
        List<Dishes> allDishesByIds = dishesServicePort.findAllByIds(ids);
        boolean allMatch = allDishesByIds.stream().allMatch(dish -> dish.getRestaurant().getId().equals(restaurantId));
        if (!allMatch) {
            throw new BadRequestValidationException(Constants.DISHES_RESTAURANT_MATCH_EXCEPTION_MESSAGE);
        }
    }

    public void validateCustomerOrder(Long customerId) {
        List<Order> byIdCustomer = findAllByIdCustomer(customerId);
        boolean allMatch = byIdCustomer.stream().allMatch(order -> order.getStatus() == OrderStatusEnum.COMPLETED);
        if (!allMatch) {
            throw new BadRequestValidationException(Constants.CUSTOMER_ACTIVE_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private OrderStatusEnum createState(OrderEntity orderEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authority = authentication.getAuthorities().iterator().next().getAuthority();

        if (orderEntity.getStatus() == OrderStatusEnum.IN_PREPARATION) {
            if (authority.equals("ROLE_" + RoleEnum.EMPLOYEE.getRoleName())) {
                return OrderStatusEnum.READY;
            } else {
                throw new BadRequestValidationException(Constants.UPDATE_ORDER_ERROR_EXCEPTION_MESSAGE);
            }
        } else {
            throw new BadRequestValidationException(Constants.UPDATE_ORDER_ERROR_EXCEPTION_MESSAGE);
        }
    }

    private void sendSMS(OrderEntity orderEntity, String deliveryCode) {
        try {
            if (orderEntity.getStatus() == OrderStatusEnum.READY) {
                ResponseEntity<User> userById = userClient.findUserById(orderEntity.getIdCustomer());
                Long cellphone = Objects.requireNonNull(userById.getBody()).getCellphone();
                String message = "Your order is ready, your security PIN is: " +deliveryCode;
                SmsBody smsBody = new SmsBody(cellphone.toString(), message);
                messagingClient.sendSms(smsBody);
            }
        } catch (Exception e) {
            throw new BadRequestValidationException(Constants.SMS_EXCEPTION_MESSAGE);
        }
    }
}
