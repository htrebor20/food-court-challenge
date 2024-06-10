package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Category;

public interface ICategoryServicePort {
    Category findById(Long id);
}
