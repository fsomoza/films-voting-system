package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.ConversionUtils;
import com.example.demo.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public Employee saveEmployee(EmployeeDTO employeeDto) {
          ValidatorUtils.validateEmployee(employeeDto, false);
          this.checkIfEmailExists(employeeDto.getEmail());
          employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
          Employee employee =  ConversionUtils.dtoToEntity(employeeDto);
          employee.setRole(Role.USER);
          return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()       // Convert the list to a stream
                .map(ConversionUtils::entityToDTO)  // Map each entity to DTO
                .collect(Collectors.toList());  // Collect results into a list
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
    public EmployeeResponseDTO getEmployeeDtoById(Long id) {
        Employee result;
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return ConversionUtils.entityToDTO(employee.get());
        }else{
            throw new EmployeeNotFoundException(id);
        }

    }

    public Boolean checkIfEmailExists(String email, long id){

        if (employeeRepository.existsByEmailAndIdNot(email,id)){
            throw new EmailAlreadyExistsException();
        }else{
            return true;
        }

    }

    public Boolean checkIfEmailExists(String email){

        if (employeeRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException();
        }else{
            return true;
        }

    }



    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(EmployeeDTO employee, Long id) throws Exception {

        ValidatorUtils.validateEmployee(employee, true);
        Employee employeeDb = this.getEmployeeById(id);
        this.checkIfEmailExists(employee.getEmail(),id);

        employeeDb.setName(employee.getName());
        employeeDb.setFrontend(employee.isFrontend());
        employeeDb.setAge(employee.getAge());
        employeeDb.setEmail(employee.getEmail());
        return ConversionUtils.entityToDTO(employeeRepository.save(employeeDb));
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        //this method already checks if the employee exists
        Employee employee = this.getEmployeeById(id);
        employeeRepository.deleteById(id);
    }
}