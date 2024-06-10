package com.pragma.food_cout.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishesRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Price is mandatory")
    private Integer price;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Image URL is mandatory")
    private String imageUrl;
    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
    @NotNull(message = "Restaurant ID is mandatory")
    private Long restaurantId;
    @NotNull(message = "Owner ID is mandatory")
    private Long ownerId;
}
