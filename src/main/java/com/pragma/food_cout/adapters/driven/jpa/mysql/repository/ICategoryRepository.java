package com.pragma.food_cout.adapters.driven.jpa.mysql.repository;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
