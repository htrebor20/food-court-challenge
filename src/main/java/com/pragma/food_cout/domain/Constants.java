package com.pragma.food_cout.domain;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String INVALID_FORMAT = "The %s format is not correct";
    public static final String ROL_VALIDATIONS_MESSAGE = "The  %s does not have permission ";
    public static final String ID_VALIDATIONS_EXCEPTION_MESSAGE = "The id %d was not found";
    public static final String ID_OWNER_VALIDATIONS_EXCEPTION_MESSAGE = "Only the restaurant owner can create dishes";
    public static final String PRICE_VALIDATIONS_EXCEPTION_MESSAGE = "The price must be integer and greater than 0";
    public static final String DISHES_RESTAURANT_MATCH_EXCEPTION_MESSAGE = "All dishes must belong to the same restaurant.";
    public static final String CUSTOMER_ACTIVE_ORDER_EXCEPTION_MESSAGE = "The client has active orders.";
    public static final String ID_FIELD_VALIDATIONS_EXCEPTION_MESSAGE = "The id %d of %s was not found";
    public static final String UPDATE_ORDER_ERROR_EXCEPTION_MESSAGE = "The order could not be updated, please contact support.";
    public static final String SMS_EXCEPTION_MESSAGE = "There was an error sending the security PIN";
}
