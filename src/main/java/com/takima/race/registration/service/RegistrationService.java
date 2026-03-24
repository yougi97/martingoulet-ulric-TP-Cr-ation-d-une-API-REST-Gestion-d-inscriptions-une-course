package com.takima.race.registration.service;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RaceRepository raceRepository;
    private final RunnerRepository runnerRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            RaceRepository raceRepository,
            RunnerRepository runnerRepository) {

        this.registrationRepository = registrationRepository;
        this.raceRepository = raceRepository;
        this.runnerRepository = runnerRepository;
    }

    public Registration register(Long raceId, Long runnerId) {

        // 1. Vérifier que la course existe
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Race not found"));

        // 2. Vérifier que le coureur existe
        Runner runner = runnerRepository.findById(runnerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Runner not found"));

        // 3. Vérifier doublon
        registrationRepository
                .findByRaceIdAndRunnerId(raceId, runnerId)
                .ifPresent(r -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Runner already registered to this race");
                });

        // 4. Vérifier capacité
        long count = registrationRepository.countByRaceId(raceId);

        if (count >= race.getMaxParticipants()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Race is full");
        }

        // 5. Création
        Registration registration = new Registration(race, runner);

        return registrationRepository.save(registration);
    }

    public List<Registration> getRegistrationsByRace(Long raceId) {
        return registrationRepository.findByRaceId(raceId);
    }

    public List<Race> getRacesByRunner(Long runnerId) {
        return registrationRepository.findByRunnerId(runnerId)
                .stream()
                .map(Registration::getRace) // ⚠️ nécessite getRace()
                .toList();
    }
}