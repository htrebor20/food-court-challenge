package com.pragma.food_cout.domain.model;

import com.pragma.food_cout.utility.enums.OrderStatusEnum;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private Long idRestaurant;
    private Long idCustomer;
    private Long assignedEmployee;
    private List<OrderDish> dishes;
    private LocalDate orderDate;
    private OrderStatusEnum status;

    public Order(Long id, Long idRestaurant, Long idCustomer, Long assignedEmployee, List<OrderDish> dishes, LocalDate orderDate, OrderStatusEnum status) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.idCustomer = idCustomer;
        this.assignedEmployee = assignedEmployee;
        this.dishes = dishes;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Long assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public List<OrderDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDish> dishes) {
        this.dishes = dishes;
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
}
