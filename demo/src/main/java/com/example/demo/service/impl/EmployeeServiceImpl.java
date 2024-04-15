package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.ConversionUtil;
import com.example.demo.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    @Transactional
    public Employee saveEmployee(EmployeeDTO employeeDto) {
          EmployeeValidator.validate(employeeDto);
          Employee employee =  ConversionUtil.dtoToEntity(employeeDto);
          return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
      return   employeeRepository.findAll();

    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee result;
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return  employee.get();
        }else{
            throw new EmployeeNotFoundException(id);
        }

    }

    @Override
    @Transactional
    public Employee updateEmployee(EmployeeDTO employee, Long id) throws Exception {

        EmployeeValidator.validate(employee);
        Employee employeeDb = this.getEmployeeById(id);

        employeeDb.setName(employee.getName());
        employeeDb.setFrontend(employee.isFrontend());
        employeeDb.setAge(employee.getAge());
        employeeDb.setEmail(employee.getEmail());
        return employeeRepository.save(employeeDb);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        //this method already checks if the employee exists
        Employee employee = this.getEmployeeById(id);
        employeeRepository.deleteById(id);
    }
}