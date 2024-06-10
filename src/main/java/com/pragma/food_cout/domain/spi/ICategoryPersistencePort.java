package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Category;

public interface ICategoryPersistencePort {
    Category findById(Long id);
}
