package com.example.demo.exception;


public class ProductionAlreadyExistsException extends RuntimeException {


    public ProductionAlreadyExistsException(String name, String prodType) {
        super("The introduced " + prodType  +": '" + name + "'" +" already exists");

    }

}
