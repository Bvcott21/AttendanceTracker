package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.Trainer;
import com.fdmgroup.attendancetracker.repository.TrainerRepository;

@Service
public class TrainerService {
    private static final Logger log = LoggerFactory.getLogger(TrainerService.class);
    private TrainerRepository trainerRepo;

    TrainerService(TrainerRepository trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    public List<Trainer> findAll() {
        return trainerRepo.findAll();
    }

    public Trainer createTrainer(Trainer trainer) {
        Trainer trainerTaken = trainerRepo.save(trainer);

        if(trainerTaken == null) {
            log.debug("Trainer already exists.");
            return null;
        }

        log.info("Trainer persisted.");
        return trainerTaken;
    }

    public Trainer getTrainer(int id) {
        log.info("Calling TrainerRepo's findById with ID: " + id);
        Optional<Trainer> optTrainer = trainerRepo.findById(id);

        if(optTrainer.isPresent()) {
            log.info("Match found.");
            return optTrainer.get();
        }

        log.debug("Not found.");
        return null;
    }
}
