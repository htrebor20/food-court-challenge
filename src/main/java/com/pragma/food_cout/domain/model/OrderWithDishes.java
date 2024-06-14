package com.pragma.food_cout.domain.model;

import com.pragma.food_cout.utility.enums.OrderStatusEnum;

import java.time.LocalDate;
import java.util.List;

public class OrderWithDishes {
    private Long id;
    private Long idCustomer;
    private LocalDate orderDate;
    private OrderStatusEnum status;
    private Restaurant restaurant;
    private List<Dishes> dishes;

    public OrderWithDishes(Long id, Long idCustomer, LocalDate orderDate, OrderStatusEnum status, Restaurant restaurant, List<Dishes> dishes) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.orderDate = orderDate;
        this.status = status;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }
}
