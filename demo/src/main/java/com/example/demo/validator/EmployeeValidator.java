package com.example.demo.validator;

import com.example.demo.dto.EmployeeDTO;

public class EmployeeValidator {
    public static void validate(EmployeeDTO employee, boolean isUpdate) {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email is not in a valid format");
        }

        if (employee.getAge() < 16) {
            throw new IllegalArgumentException("Age must be 16 or higher");
        }

        if (!isUpdate){
            if (employee.getPassword() == null || employee.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
            if (!employee.getPassword().matches(passwordRegex)) {
                throw new IllegalArgumentException("Password must be at least 8 characters long and include a number, an uppercase letter, a lowercase letter, and a special character");
            }
        }

    }
}
