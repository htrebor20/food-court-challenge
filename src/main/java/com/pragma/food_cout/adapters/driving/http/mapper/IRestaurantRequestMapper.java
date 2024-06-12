package com.pragma.food_cout.adapters.driving.http.mapper;

import com.pragma.food_cout.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.CustomerRestaurantResponseDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    Restaurant toModel(RestaurantRequestDto restaurantRequestDto);

    RestaurantResponseDto toResponse(Restaurant restaurant);

    List<CustomerRestaurantResponseDto> toResponseRestaurantList(List<Restaurant> restaurantCustomPage);

    CustomPage<CustomerRestaurantResponseDto> toResponseDtoList(CustomPage<Restaurant> customPage);
    default CustomPage<CustomerRestaurantResponseDto> toCustomPage(CustomPage<Restaurant> restaurantPage) {
        return new CustomPage<>(
                this.toResponseRestaurantList(restaurantPage.getContent()),
                restaurantPage.getTotalElements(),
                restaurantPage.getTotalPages(),
                restaurantPage.getNumber(),
                restaurantPage.getSize()
        );
    }
}

