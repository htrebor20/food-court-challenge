package com.pragma.food_cout.adapters.driving.http.dto.response;

import com.pragma.food_cout.domain.model.Category;
import com.pragma.food_cout.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishesResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Category category;
    boolean active;
    private Restaurant restaurant;
}
