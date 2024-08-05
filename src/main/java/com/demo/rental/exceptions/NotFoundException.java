package com.demo.rental.exceptions;

/**
 * Exception to pass not found values
 */
public class NotFoundException extends Exception{

    public NotFoundException(String msg){
        super(msg);
    }

}
