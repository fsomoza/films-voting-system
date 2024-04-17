package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ProductionNotFoundException extends RuntimeException {
    // Getter method for the employee ID
    private final Long productionId;

    // Constructor with the ID of the not found employee
    public ProductionNotFoundException(Long productionId) {
        super("Production with id " + productionId + " not found");
        this.productionId = productionId;
    }

}
