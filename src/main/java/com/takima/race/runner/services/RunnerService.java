package com.takima.race.runner.services;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }
    public Runner create(Runner runner){
        return runnerRepository.save(runner);
    }

    public void delete(Long id) {
        Runner runner = runnerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Runner not found with id " + id
                ));
        runnerRepository.delete(runner);
    }


    public Runner update(Long id, Runner newRunner) {

        Runner runner = runnerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Runner not found"
                ));

        runner.setFirstName(newRunner.getFirstName());
        runner.setLastName(newRunner.getLastName());
        runner.setEmail(newRunner.getEmail());
        runner.setAge(newRunner.getAge());

        return runnerRepository.save(runner);
    }

    public Runner createRunner(Runner runner) {

        if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email invalide"
            );
        }

        return runnerRepository.save(runner);
    }
}
