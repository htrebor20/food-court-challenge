package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.food_cout.domain.model.Dishes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesEntityMapper {

    Dishes toModel(DishesEntity dishesEntity);
    DishesEntity  toEntity(Dishes  dishes);
}
