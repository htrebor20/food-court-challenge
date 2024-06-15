package com.pragma.food_cout.adapters.driving.http.controller;

import com.pragma.food_cout.adapters.ConstantsAdapters;
import com.pragma.food_cout.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.CustomerRestaurantResponseDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.food_cout.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.utility.CustomPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor

public class RestaurantRestController {
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @PostMapping("/")
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody @Valid RestaurantRequestDto request) {
        Restaurant restaurant = restaurantRequestMapper.toModel(request);
        Restaurant saveRestaurant = restaurantServicePort.saveRestaurant(restaurant);
        RestaurantResponseDto response = restaurantRequestMapper.toResponse(saveRestaurant);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CustomPage<CustomerRestaurantResponseDto>> getAllRestaurant(@RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                                                      @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size) {
        CustomPage<Restaurant> response = restaurantServicePort.getAll(page, size);
        return ResponseEntity.ok(restaurantRequestMapper.toResponseDtoList(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> findRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantServicePort.findById(id);
        RestaurantResponseDto response = restaurantRequestMapper.toResponse(restaurant);
        return ResponseEntity.ok(response);
    }
}
