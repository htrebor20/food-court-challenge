package com.pragma.food_cout.adapters.driving.http.mapper;

import com.pragma.food_cout.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.DishesResponseDto;
import com.pragma.food_cout.domain.model.Category;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesRequestMapper {
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "restaurant", source = "restaurantId")
    Dishes requestToModel(DishesRequestDto dishesRequestDto);

    default Category mapCategory(Long categoryId) {
        return new Category(categoryId, null, null);
    }

    default Restaurant mapRestaurant(Long restaurantId) {
        return new Restaurant(restaurantId, null, null, null, null, null, null);
    }

    DishesResponseDto toResponse(Dishes dishes);
}
