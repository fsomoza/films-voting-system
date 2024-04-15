package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private String name;
    private String email;
    private boolean frontend;
    private int age;
}
