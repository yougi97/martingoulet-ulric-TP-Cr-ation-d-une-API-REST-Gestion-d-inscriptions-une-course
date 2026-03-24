package com.takima.race.race.entities;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate date;
    private String location;
    private Integer maxParticipants;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id, race.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }



    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public String getLocation() { return location; }
    public Integer getMaxParticipants() { return maxParticipants; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }


    public Collection<Object> getRunners() {
        return List.of();
    }
}