package com.example.demo.exception;


public class FilmAlreadyExistsException extends RuntimeException {


    public FilmAlreadyExistsException(String name) {
        super("The introduced film: " +"'" + name + "'" +" already exists");

    }

}
