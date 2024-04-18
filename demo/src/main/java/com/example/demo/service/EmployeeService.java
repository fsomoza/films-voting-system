package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(EmployeeDTO employee, boolean isFirst);
    List<EmployeeResponseDTO> getAllEmployees();
    Employee getEmployeeById(Long id);

    EmployeeResponseDTO getEmployeeDtoById(Long id);

    EmployeeResponseDTO updateEmployee(EmployeeDTO employee, Long id) throws Exception;
    void deleteEmployee(Long id);
}
