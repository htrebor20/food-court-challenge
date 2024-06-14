package com.pragma.food_cout.adapters.driving.http.dto.response;

import com.pragma.food_cout.domain.model.OrderDish;
import com.pragma.food_cout.utility.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
    private Long idRestaurant;
    private Long idCustomer;
    private List<OrderDish> dishes;
    private LocalDate orderDate;
    private OrderStatusEnum status;
}
