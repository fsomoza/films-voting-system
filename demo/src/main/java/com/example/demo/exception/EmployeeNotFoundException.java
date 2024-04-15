package com.example.demo.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {
    // Getter method for the employee ID
    private final Long employeeId;

    // Constructor with the ID of the not found employee
    public EmployeeNotFoundException(Long employeeId) {
        super("Employee with id " + employeeId + " not found");
        this.employeeId = employeeId;
    }

}
