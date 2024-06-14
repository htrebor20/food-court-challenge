package com.pragma.food_cout.utility.enums;

import java.util.Arrays;

public enum OrderStatusEnum {

    PENDING("PENDING"),
    IN_PREPARATION("IN_PREPARATION"),
    READY("READY"),
    CANCELED("CANCELED"),
    COMPLETED("COMPLETED");

    private final String statusName;

    private OrderStatusEnum(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public boolean isValidRole(String statusName) {
        return Arrays.stream(OrderStatusEnum.values())
                .anyMatch(roleEnum -> roleEnum.getStatusName().equalsIgnoreCase(statusName));
    }
}
