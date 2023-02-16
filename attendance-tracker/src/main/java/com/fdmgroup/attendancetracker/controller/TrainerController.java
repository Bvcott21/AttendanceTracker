package com.fdmgroup.attendancetracker.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.attendancetracker.model.Trainer;
import com.fdmgroup.attendancetracker.service.TrainerService;

@RestController
@RequestMapping("api/v1/trainer")
@CrossOrigin(origins="http://localhost:3000")
public class TrainerController {
    private static final Logger log = LoggerFactory.getLogger(TrainerController.class);
    private TrainerService trainerServ;
    
    TrainerController(TrainerService trainerServ) {
        this.trainerServ = trainerServ;
    }

    @GetMapping
    List<Trainer> listAll() {
        return trainerServ.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Trainer> displayTrainerDetails(@PathVariable int id) {
        log.info("Calling TrainerService's findTrainer passing ID: " + id);
        Trainer trainer = trainerServ.getTrainer(id);

        if(trainer != null) {
            log.info("Trainer found.");
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(trainer);
        }
        log.debug("Trainer not found");
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    @PostMapping
    ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        Trainer trainerCreated = trainerServ.createTrainer(trainer);

        if(trainerCreated != null) {
            log.info("Trainer: " + trainerCreated + " created successfully.");
            URI loc = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .replacePath("api/v1/user/{id}")
                .buildAndExpand(trainerCreated.getId())
                .toUri();

            return ResponseEntity
                .created(loc)
                .build();
        }

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
    }
}
