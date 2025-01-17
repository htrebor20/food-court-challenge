package com.pragma.food_cout.adapters.driven.jpa.mysql.repository;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Page<RestaurantEntity> findAll(Pageable pageable);
}

