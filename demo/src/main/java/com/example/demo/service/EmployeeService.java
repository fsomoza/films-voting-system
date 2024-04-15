package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.model.Employee;

public interface EmployeeService {
    EmployeeResponseDTO saveEmployee(EmployeeDTO employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployee(EmployeeDTO employee, Long id) throws Exception;
    void deleteEmployee(Long id);
}
