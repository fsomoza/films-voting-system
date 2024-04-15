package com.example.demo.validator;

import com.example.demo.dto.EmployeeDTO;

public class EmployeeValidator {
    public static void validate(EmployeeDTO employee) {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email is not in a valid format");
        }

        if (employee.getAge() < 16) {
            throw new IllegalArgumentException("Age must be 16 or higher");
        }
    }
}
