package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_cout.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantEntityMapper {

    Restaurant toModel(RestaurantEntity restaurant);
    RestaurantEntity toEntity(Restaurant restaurant);
}
