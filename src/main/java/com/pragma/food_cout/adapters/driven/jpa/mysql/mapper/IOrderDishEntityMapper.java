package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.food_cout.domain.model.OrderDishRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {
    OrderDishRequest toModel(OrderDishEntity orderDishEntity);
    OrderDishEntity toEntity(OrderDishRequest orderDish);
}
