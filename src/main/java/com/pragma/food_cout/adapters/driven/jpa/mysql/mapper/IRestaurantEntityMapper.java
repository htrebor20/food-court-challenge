package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    Restaurant toModel(RestaurantEntity restaurant);
    RestaurantEntity toEntity(Restaurant restaurant);

    List<Restaurant> toRestaurantList(List<RestaurantEntity> bootcampList);

    CustomPage<Restaurant> toPage(Page<RestaurantEntity> entityPage);
    default CustomPage<Restaurant> toCustomPage(Page<RestaurantEntity> entityPage) {
        return new CustomPage<>(
                this.toRestaurantList(entityPage.getContent()),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getNumber(),
                entityPage.getSize()
        );
    }
}

