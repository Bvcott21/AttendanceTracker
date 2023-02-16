package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.repository.TraineeRepository;

@Service
public class TraineeService {
    private static final Logger log = LoggerFactory.getLogger(TraineeService.class);
    private TraineeRepository traineeRepo;

    TraineeService(TraineeRepository traineeRepo) {
        this.traineeRepo = traineeRepo;
    }

    public List<Trainee> findAll() {
        return traineeRepo.findAll();
    }

    public Trainee createTrainee(Trainee trainee) {
        Trainee traineeTaken = traineeRepo.save(trainee);

        if(traineeTaken == null) {
            log.debug("Trainee already exists.");
            return null;
        }

        log.info("Trainee persisted.");
        return traineeTaken;
    }

    public Trainee getTrainee(int id) {
        log.info("Calling TraineeRepo's findById with ID: " + id);
        Optional<Trainee> optTrainee = traineeRepo.findById(id);

        if(optTrainee.isPresent()) {
            log.info("Match found.");
            return optTrainee.get();
        }

        log.debug("Not found.");
        return null;
    }
}
