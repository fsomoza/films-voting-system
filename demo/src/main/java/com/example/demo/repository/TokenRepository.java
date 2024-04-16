package com.example.demo.repository;

import com.example.demo.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("select t from Token t where t.employee.id = :employeeId and t.loggedOut = false")
    List<Token> findAllTokensByUser(@Param("employeeId") long employeeId);


    Optional<Token> findByToken(String token);
}
