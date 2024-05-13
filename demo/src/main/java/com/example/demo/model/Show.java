package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("SHOW")
public class Show  extends Production{
    private int seasons;
}
