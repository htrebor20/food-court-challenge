package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.utility.CustomPage;

import java.util.List;

public interface IDishesServicePort {
    Dishes save(Dishes dishes);
    Dishes update(Dishes dishes, Long id);
    Dishes findById(Long id);
    CustomPage<Dishes> getAll(Integer page, Integer size, Long categoryId);
    List<Dishes> findAllByIds(List<Long> ids);
}
