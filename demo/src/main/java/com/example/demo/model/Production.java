package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "prod_type", discriminatorType = DiscriminatorType.STRING)
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
    @OneToMany(mappedBy = "production" , fetch = FetchType.LAZY)
    private List<Vote> votes;
    //total_votes se inicializa  a 0 porque si no el trigger que tengo para que sume los votos no funciona si está a null
    //porque obviamente no se puede sumar sobre un nulo
    private Long totalVotes = 0L;
}

