package com.takima.race.race.repositories;

import com.takima.race.race.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {

}