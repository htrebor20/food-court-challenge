package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.utility.CustomPage;

public interface IDishesServicePort {
    Dishes save(Dishes dishes);
    Dishes update(Dishes dishes, Long id);
    CustomPage<Dishes> getAll(Integer page, Integer size, Long categoryId);
}
