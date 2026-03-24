package com.takima.race.registration.entities;

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Registration {

    private LocalDate registrationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Race race;
    @ManyToOne
    private Runner runner;



    public Registration(LocalDate registrationDate){
        this.registrationDate = registrationDate;
    }
    public Registration(Race race, Runner runner) {
        this.race = race;
        this.runner = runner;
        this.registrationDate = LocalDate.now();
    }

    public Registration() {

    }


    // Getters
    public Long getId() { return id; }
    public Race getRace() {return race;}




    // Setters
    public void setId(Long id) { this.id = id; }


    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }
}