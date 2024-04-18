package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Production;
import com.example.demo.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Employee> findByEmail(String email);


}
