package com.pragma.food_cout.domain.enums;

import java.util.Arrays;

public enum RoleEnum {

    ADMIN("ADMIN"),
    OWNER("OWNER"),
    EMPLOYEE("EMPLOYEE"),
    CUSTOMER("CUSTOMER");

    private String roleName;

    private RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public boolean isValidRole ( String roleName){
        return Arrays.asList(RoleEnum.values()).stream()
                .anyMatch(roleEnum -> roleEnum.getRoleName().equalsIgnoreCase(roleName));
    }
}
