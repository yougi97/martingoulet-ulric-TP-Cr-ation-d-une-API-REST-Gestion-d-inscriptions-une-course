package com.takima.race.registration.repositories;

import com.takima.race.registration.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByRaceIdAndRunnerId(Long raceId, Long runnerId);

    List<Registration> findByRaceId(Long raceId);

    List<Registration> findByRunnerId(Long runnerId);

    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);

    long countByRaceId(Long raceId);

}