package com.takima.race.race.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository; // <- Repository, pas Service
    private final RunnerRepository runnerRepository; // <- Repository, pas Service

    public RaceService(RaceRepository raceRepository, RunnerRepository runnerRepository) {
        this.raceRepository = raceRepository;
        this.runnerRepository = runnerRepository;
    }

    public List<Race> getAll() {
        return raceRepository.findAll();
    }

    public Race create(Race race) {

        return raceRepository.save(race);
    }

    public Race getById(Long id){
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", id)
                )
        );
    }

    public int countParticipants(Long raceId) {

        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Race not found"
                ));

        return race.getRunners().size();
    }

    public void registerRunner(Long raceId, Long runnerId) {

        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Race not found"
                ));

        Runner runner = runnerRepository.findById(runnerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Runner not found"
                ));

        race.getRunners().add(runner);

        raceRepository.save(race);
    }







}