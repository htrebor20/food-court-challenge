package com.pragma.food_cout.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DishesRequestUpdateDto {
    private Integer price;
    private String description;
}
