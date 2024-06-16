package com.pragma.food_cout.adapters.driving.http.controller;

import com.pragma.food_cout.adapters.ConstantsAdapters;
import com.pragma.food_cout.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.food_cout.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.food_cout.domain.api.IOrderServicePort;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.utility.CustomPage;
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

    @GetMapping("/")
    public ResponseEntity<CustomPage<OrderResponseDto>> getAllOrder(@RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                                    @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size,
                                                                    @RequestParam(required = false) String status) {
        CustomPage<OrderWithDishes> response = orderServicePort.getAllByStatus(page, size,status);
        return ResponseEntity.ok(orderRequestMapper.toResponseDtoList(response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> assignEmployee(@PathVariable(name = "id") Long id, @RequestParam  Long idEmployee  ) {
        orderServicePort.assignEmployee(idEmployee, id);
        return new ResponseEntity<>("Order assign", HttpStatus.CREATED);
    }

    @PatchMapping("/update-state/{id}")
    public ResponseEntity<String> updateOrderState(@PathVariable(name = "id") Long id, @RequestParam  String deliveryCode   ) {
        orderServicePort.updateOrderState(id, deliveryCode);
        return new ResponseEntity<>("Order updated", HttpStatus.CREATED);
    }
}
