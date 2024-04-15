package com.example.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private Long employeeId;

    // Constructor with the ID of the not found employee
    public EmployeeNotFoundException(Long employeeId) {
        super("Employee with id " + employeeId + " not found");
        this.employeeId = employeeId;
    }

    // Getter method for the employee ID
    public Long getEmployeeId() {
        return employeeId;
    }
}
