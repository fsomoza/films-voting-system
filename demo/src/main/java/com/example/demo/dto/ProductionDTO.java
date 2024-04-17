package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionDTO {
    private String title;
    private int year;
    private String director;
    private String genre;
    private Long proposerId;
    private double averageScore;
    private Long registerId;
    private LocalDateTime registerDate;
}