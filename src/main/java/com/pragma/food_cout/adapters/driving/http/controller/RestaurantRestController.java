package com.pragma.food_cout.adapters.driving.http.controller;

import com.pragma.food_cout.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.food_cout.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.model.Restaurant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor

public class RestaurantRestController {
        private final IRestaurantRequestMapper restaurantRequestMapper;
        private final IRestaurantServicePort restaurantServicePort;

        @PostMapping("/")
        public ResponseEntity<Void> createRestaurant(@RequestBody @Valid RestaurantRequestDto request) {
            Restaurant restaurant = restaurantRequestMapper.toModel(request);
            restaurantServicePort.saveRestaurant(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
