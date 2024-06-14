package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    @Mapping(target = "idRestaurant", source = "restaurant")
    Order toModel(OrderEntity orderEntity);

    OrderEntity toEntity(Order order);

    List<Order> toOrderList(List<OrderEntity> orderList);

    default Long map(RestaurantEntity restaurantEntity) {
        return restaurantEntity.getId();
    }

    CustomPage<Order> toPage(Page<OrderEntity> entityPage);

    default CustomPage<Order> toCustomPage(Page<OrderEntity> entityPage) {
        return new CustomPage<>(
                this.toOrderList(entityPage.getContent()),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getNumber(),
                entityPage.getSize()
        );
    }

    @Mapping(target = "dishes", source = "orderDishes")
    OrderWithDishes toOrderWithDishes(OrderEntity orderEntity);

    List<OrderWithDishes> toOrderWithDishesList(List<OrderEntity> orderList);

    Restaurant toRestaurantModel(RestaurantEntity restaurant);

    CustomPage<OrderWithDishes> toPageOrderWithDishes(Page<OrderEntity> entityPage);

    default CustomPage<OrderWithDishes> toCustomPageOrderWithDishes(Page<OrderEntity> entityPage) {
        return new CustomPage<>(
                this.toOrderWithDishesList(entityPage.getContent()),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getNumber(),
                entityPage.getSize()
        );
    }

    default Dishes mapToDishes(OrderDishEntity dishesEntity) {
        return new Dishes(
                dishesEntity.getDish().getId(),
                dishesEntity.getDish().getName(),
                dishesEntity.getDish().getPrice(),
                dishesEntity.getDish().getDescription(),
                dishesEntity.getDish().getImageUrl(),
                null,
                dishesEntity.getDish().isActive(),
                toRestaurantModel(dishesEntity.getDish().getRestaurant()),
                null
        );
    }
}

