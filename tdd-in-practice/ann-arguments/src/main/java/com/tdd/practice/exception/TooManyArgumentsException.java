package com.tdd.practice.exception;

public class TooManyArgumentsException extends RuntimeException{

    private final String option;

    public TooManyArgumentsException(String option) {
        super(option);
        this.option = option;
    }

    public String getOption() {
        return option;
    }

}
