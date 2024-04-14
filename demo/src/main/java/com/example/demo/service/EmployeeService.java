package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(EmployeeDTO employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(EmployeeDTO employee, Long id) throws Exception;
    void deleteEmployee(Long id);
}
