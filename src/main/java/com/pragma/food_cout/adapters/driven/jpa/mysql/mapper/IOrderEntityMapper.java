package com.pragma.food_cout.adapters.driven.jpa.mysql.mapper;
import com.pragma.food_cout.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_cout.domain.model.Order;
import com.pragma.food_cout.utility.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    Order toModel(OrderEntity orderEntity);
    OrderEntity toEntity(Order order);

    List<Order> toOrderList(List<OrderEntity> orderList);

    CustomPage<Order> toPage(Page<OrderEntity> entityPage);
    default CustomPage<Order> toCustomPage(Page<OrderEntity> entityPage) {
        return new CustomPage<>(
                this.toOrderList(entityPage.getContent()),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getNumber(),
                entityPage.getSize()
        );
    }
}
