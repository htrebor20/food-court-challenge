package com.pragma.food_cout.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishesRequestDto {
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private Long restaurantId;
    private Long ownerId;
}
