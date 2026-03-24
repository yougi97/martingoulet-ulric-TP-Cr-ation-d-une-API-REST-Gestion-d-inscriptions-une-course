package com.takima.race.race.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getAllRaces() {
        return raceService.getAll();
    }

    @PostMapping
    public Race createRace(@RequestBody Race race){
        return raceService.create(race);
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id){

        return raceService.getById(id);
    }

    @GetMapping("/{raceId}/participants/count")
    public Map<String, Integer> countParticipants(@PathVariable Long raceId) {

        int count = raceService.countParticipants(raceId);

        return Map.of("count", count);
    }



}
