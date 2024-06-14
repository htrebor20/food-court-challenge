package com.pragma.food_cout.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OrderRequestDto {
    private Long idRestaurant;
    private Long idCustomer;
    private List<OrderDishRequestDto> dishes;
}
