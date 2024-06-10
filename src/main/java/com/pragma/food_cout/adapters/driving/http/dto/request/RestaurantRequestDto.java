package com.pragma.food_cout.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RestaurantRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Tax ID is mandatory")
    private Long  taxId;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotNull(message = "Phone is mandatory")
    private Long phone;
    @NotBlank(message = "Logo URL is mandatory")
    private String logoUrl;
    @NotNull(message = "Owner ID is mandatory")
    private Long ownerId;
}
