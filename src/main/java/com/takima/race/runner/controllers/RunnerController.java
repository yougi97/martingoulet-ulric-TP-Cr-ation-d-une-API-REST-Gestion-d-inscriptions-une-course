package com.takima.race.runner.controllers;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {

        return runnerService.getById(id);
    }

    @PostMapping
    public Runner createRunner(@Valid @RequestBody Runner runner) {
        return runnerService.createRunner(runner);
    }

    // Supprimer un runner par ID
    @DeleteMapping("/{id}")
    public void deleteRunner(@PathVariable Long id) {
        runnerService.delete(id);
    }

    //Modifier un runners
    @PutMapping("/{id}")
    public Runner updateRunner(@PathVariable Long id, @RequestBody Runner runner) {
        return runnerService.update(id, runner);
    }




}