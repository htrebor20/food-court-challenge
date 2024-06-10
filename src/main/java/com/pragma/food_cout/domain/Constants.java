package com.pragma.food_cout.domain;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String INVALID_FORMAT = "the %s format is not correct";
    public static final String ROL_VALIDATIONS_MESSAGE = "the  %s does not have permission ";
    public static final String ID_VALIDATIONS_EXCEPTION_MESSAGE = "the id %d was not found";
    public static final String ID_OWNER_VALIDATIONS_EXCEPTION_MESSAGE = "Only the restaurant owner can create dishes";
    public static final String PRICE_VALIDATIONS_EXCEPTION_MESSAGE = "the price must be integer and greater than 0";
}
