package com.pragma.food_cout.adapters.driven.jpa.mysql.repository;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.DishesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishesRepository extends JpaRepository<DishesEntity, Long> {
    Page<DishesEntity> findByCategory_Id(Long categoryId, Pageable pageable);
}
