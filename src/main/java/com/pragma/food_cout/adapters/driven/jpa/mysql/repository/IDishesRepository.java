package com.pragma.food_cout.adapters.driven.jpa.mysql.repository;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishesRepository extends JpaRepository<DishesEntity, Long> {
}
