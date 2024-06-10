package com.pragma.food_cout.domain.api;

import com.pragma.food_cout.domain.model.Dishes;

public interface IDishesServicePort {
    Dishes save(Dishes dishes);
}
