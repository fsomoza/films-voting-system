package com.example.demo.dto;

import lombok.*;
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO extends ProductionDTO {
    private int length;
}