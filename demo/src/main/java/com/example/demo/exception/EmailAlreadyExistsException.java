package com.example.demo.exception;

import lombok.Getter;


public class EmailAlreadyExistsException extends RuntimeException {


    public EmailAlreadyExistsException() {
        super("The introduced email already exists");

    }

}
