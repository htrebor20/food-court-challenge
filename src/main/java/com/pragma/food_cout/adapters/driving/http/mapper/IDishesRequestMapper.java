package com.pragma.food_cout.adapters.driving.http.mapper;

import com.pragma.food_cout.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.request.DishesRequestUpdateDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.CustomerDishesResponseDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.DishesResponseDto;
import com.pragma.food_cout.domain.model.Category;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesRequestMapper {
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "restaurant", source = "restaurantId")
    Dishes requestToModel(DishesRequestDto dishesRequestDto);
    Dishes requestUpdateToModel(DishesRequestUpdateDto dishesRequestDto);

    default Category mapCategory(Long categoryId) {
        return new Category(categoryId, null, null);
    }

    default Restaurant mapRestaurant(Long restaurantId) {
        return new Restaurant(restaurantId, null, null, null, null, null, null);
    }

    DishesResponseDto toResponse(Dishes dishes);

    List<CustomerDishesResponseDto> toResponseDishesList(List<Dishes> dishesCustomPage);

    CustomPage<CustomerDishesResponseDto> toResponseDtoList(CustomPage<Dishes> customPage);
    default CustomPage<CustomerDishesResponseDto> toCustomPage(CustomPage<Dishes> dishesPage) {
        return new CustomPage<>(
                this.toResponseDishesList(dishesPage.getContent()),
                dishesPage.getTotalElements(),
                dishesPage.getTotalPages(),
                dishesPage.getNumber(),
                dishesPage.getSize()
        );
    }
}
