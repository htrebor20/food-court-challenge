package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_cout.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    Restaurant toModel(RestaurantEntity restaurant);
    RestaurantEntity toEntity(Restaurant restaurant);
}
