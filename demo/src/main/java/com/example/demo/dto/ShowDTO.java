package com.example.demo.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO extends ProductionDTO {
    private int seasons;
}