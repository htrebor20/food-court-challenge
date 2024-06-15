package com.pragma.food_cout.adapters.driving.http.mapper;

import com.pragma.food_cout.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.food_cout.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.domain.model.OrderWithDishes;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderRequestMapper {

    Order toModel(OrderRequestDto orderRequestDto);

    OrderResponseDto toResponse(Order order);

    List<OrderResponseDto> toResponseOrderList(List<OrderWithDishes> orderCustomPage);

    CustomPage<OrderResponseDto> toResponseDtoList(CustomPage<OrderWithDishes> customPage);

    default CustomPage<OrderResponseDto> toCustomPage(CustomPage<OrderWithDishes> orderPage) {
        return new CustomPage<>(
                this.toResponseOrderList(orderPage.getContent()),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                orderPage.getNumber(),
                orderPage.getSize()
        );
    }
}
