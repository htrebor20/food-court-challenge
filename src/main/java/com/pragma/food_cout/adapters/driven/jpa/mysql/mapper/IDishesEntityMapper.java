package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesEntityMapper {

    Dishes toModel(DishesEntity dishesEntity);
    DishesEntity  toEntity(Dishes  dishes);


    List<Dishes> toDishesList(List<DishesEntity> dishesList);

    CustomPage<Dishes> toPage(Page<DishesEntity> entityPage);
    default CustomPage<Dishes> toCustomPage(Page<DishesEntity> entityPage) {
        return new CustomPage<>(
                this.toDishesList(entityPage.getContent()),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getNumber(),
                entityPage.getSize()
        );
    }
}
