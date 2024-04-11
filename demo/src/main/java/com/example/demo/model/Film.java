package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Table;

@Value
@Entity
@Table(name = "films")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Film extends Production{
    private int length;
}
