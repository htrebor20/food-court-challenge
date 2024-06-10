package com.pragma.food_cout.domain;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String INVALID_FORMAT = "the %s format is not correct";
    public static final String ROL_VALIDATIONS_MESSAGE = "the  %s does not have permission ";
    public static final String ID_VALIDATIONS_EXCEPTION_MESSAGE = "the id %d was not found";
}
