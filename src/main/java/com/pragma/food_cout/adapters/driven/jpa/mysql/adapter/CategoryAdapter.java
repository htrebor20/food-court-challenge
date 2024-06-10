package com.pragma.food_cout.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.food_cout.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma.food_cout.domain.Constants;
import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import com.pragma.food_cout.domain.model.Category;
import com.pragma.food_cout.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
private final ICategoryRepository categoryRepository;
private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public Category findById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        if (entity.isPresent()){
            CategoryEntity categoryEntity = entity.get();
            return categoryEntityMapper.toModel(categoryEntity);
        } else {
            throw new BadRequestValidationException(String.format(Constants.ID_VALIDATIONS_EXCEPTION_MESSAGE, id));
        }
    }
}
