package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.food_cout.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    Category toModel(CategoryEntity categoryEntity);
    CategoryEntity toEntity(Category category);
}
