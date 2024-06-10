package com.pragma.food_cout.adapters.driving.http.mapper;

import com.pragma.food_cout.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.food_cout.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    Restaurant toModel(RestaurantRequestDto restaurantRequestDto);
}
