package com.pragma.food_cout.adapters.driven.jpa.mysql.repository;

import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_cout.utility.enums.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT DISTINCT o FROM OrderEntity o " +
            "LEFT JOIN FETCH o.orderDishes od " +
            "LEFT JOIN FETCH od.dish " +
            "WHERE o.status = :status")
    Page<OrderEntity> findByStatus(@Param("status") OrderStatusEnum status, Pageable pageable);
    List<OrderEntity> findByIdCustomer(Long customerId);
}
