package com.pragma.food_cout.domain.spi;

import com.pragma.food_cout.domain.model.Dishes;

public interface IDishesPersistencePort {
    Dishes saveDishes(Dishes dishes);
    Dishes findById(Dishes dishes);
}
