package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int year;
    private String director;
    private String genre;
    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private Employee proposer;
    private double averageScore;
    @ManyToOne
    @JoinColumn(name = "register_id")
    private Employee register;
    private LocalDateTime registerDate;
}

