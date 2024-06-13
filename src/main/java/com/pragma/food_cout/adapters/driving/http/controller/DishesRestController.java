package com.pragma.food_cout.adapters.driving.http.controller;

import com.pragma.food_cout.adapters.ConstantsAdapters;
import com.pragma.food_cout.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.request.DishesRequestUpdateDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.CustomerDishesResponseDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.DishesResponseDto;
import com.pragma.food_cout.adapters.driving.http.mapper.IDishesRequestMapper;
import com.pragma.food_cout.domain.api.IDishesServicePort;
import com.pragma.food_cout.domain.model.Dishes;
import com.pragma.food_cout.utility.CustomPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishesRestController {
    private final IDishesRequestMapper dishesRequestMapper;
    private final IDishesServicePort dishesServicePort;

    @PostMapping("/")
    public ResponseEntity<DishesResponseDto> createDishes(@RequestBody @Valid DishesRequestDto request) {
        Dishes dishes = dishesRequestMapper.requestToModel(request);
        Dishes savedDish = dishesServicePort.save(dishes);
        DishesResponseDto response = dishesRequestMapper.toResponse(savedDish);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DishesResponseDto> updateDish(@PathVariable(name = "id") Long id, @RequestBody DishesRequestUpdateDto request) {
        Dishes dishes = dishesRequestMapper.requestUpdateToModel(request);
        Dishes savedDish =  dishesServicePort.update(dishes, id);
        DishesResponseDto response = dishesRequestMapper.toResponse(savedDish);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CustomPage<CustomerDishesResponseDto>> getAllDishes(@RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                                              @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size,
                                                                              @RequestParam(required = false) Long categoryId) {
        CustomPage<Dishes> response = dishesServicePort.getAll(page, size, categoryId);
        return ResponseEntity.ok(dishesRequestMapper.toResponseDtoList(response));
    }
}
