package com.takima.race.registration.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.takima.race.runner.RunnerRequest;

import java.util.List;


@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/races/{raceId}/registrations")
    public ResponseEntity<Registration> register(
            @PathVariable Long raceId,
            @RequestBody RunnerRequest request) {

        Registration registration =
                registrationService.register(raceId, request.getRunnerId());

        return ResponseEntity.status(201).body(registration);
    }

    @GetMapping("/races/{raceId}/registrations")
    public List<Registration> getRegistrationsByRace(@PathVariable Long raceId) {
        return registrationService.getRegistrationsByRace(raceId);
    }

    @GetMapping("/runners/{runnerId}/races")
    public List<Race> getRacesByRunner(@PathVariable Long runnerId) {
        return registrationService.getRacesByRunner(runnerId);
    }

}