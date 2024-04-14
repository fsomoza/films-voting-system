package com.example.demo.utils;

import com.example.demo.dto.EmployeeDTO;
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
        return entity;
    }
}
