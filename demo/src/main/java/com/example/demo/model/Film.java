package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("FILM")
public class Film extends Production {
    private int length;
}
