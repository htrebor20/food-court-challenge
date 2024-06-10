package com.pragma.food_cout.domain.api.usecase;

import com.pragma.food_cout.domain.api.ICategoryServicePort;
import com.pragma.food_cout.domain.model.Category;
import com.pragma.food_cout.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort{
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category findById(Long id) {
        return categoryPersistencePort.findById(id);
    }
}
