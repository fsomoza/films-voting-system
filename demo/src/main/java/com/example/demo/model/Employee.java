package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private boolean isFrontend; // true para frontend, false para backend
    private int age;
}
