package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.utility.CustomPage;

public interface IDishesPersistencePort {
    Dishes saveDishes(Dishes dishes);
    Dishes findById(Long id);
    CustomPage<Dishes> getAll(Integer page, Integer size, Long categoryId);
}
