package com.pragma.food_cout.adapters.driving.http.controller;

import com.pragma.food_cout.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.food_cout.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.food_cout.domain.api.IOrderServicePort;
import com.pragma.food_cout.domain.model.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor

public class OrderRestController {
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderServicePort orderServicePort;

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequestDto request) {
        Order order = orderRequestMapper.toModel(request);
        orderServicePort.save(order);
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }
}
