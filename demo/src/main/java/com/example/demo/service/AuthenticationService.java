package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.Employee;
import com.example.demo.model.Token;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    private final EmployeeService employeeService;

    public AuthenticationService(EmployeeRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager,
                                 EmployeeService employeeService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.employeeService = employeeService;
    }

    public AuthenticationResponse register(EmployeeDTO request) {

       Employee employee = employeeService.saveEmployee(request);

        String jwt = jwtService.generateToken(employee);

        saveUserToken(jwt, employee);

        return new AuthenticationResponse(jwt, "User registration was successful");

    }

    public AuthenticationResponse authenticate(Employee request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Employee employee = repository.findByEmail(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(employee);

        revokeAllTokenByUser(employee);
        saveUserToken(jwt, employee);

        return new AuthenticationResponse(jwt, "User login was successful");

    }
    private void revokeAllTokenByUser(Employee employee) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(employee.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, Employee employee) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setEmployee(employee);
        tokenRepository.save(token);
    }
}
