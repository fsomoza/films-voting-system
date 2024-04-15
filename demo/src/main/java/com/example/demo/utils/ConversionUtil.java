package com.example.demo.utils;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.ProductionDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Production;

import java.lang.reflect.Field;

public class ConversionUtil {


    public static Employee dtoToEntity(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setFrontend(dto.isFrontend());
        entity.setAge(dto.getAge());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static EmployeeResponseDTO entityToDto(Employee entity) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setFrontend(entity.isFrontend());
        dto.setAge(entity.getAge());
        return dto;
    }
}
