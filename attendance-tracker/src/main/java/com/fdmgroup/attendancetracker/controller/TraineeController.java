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

import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.service.TraineeService;

@RestController
@RequestMapping("api/v1/trainee")
@CrossOrigin(origins="http://localhost:3000")
public class TraineeController {
    private static final Logger log = LoggerFactory.getLogger(TraineeController.class);
    private TraineeService traineeServ;
    
    TraineeController(TraineeService traineeServ) {
        this.traineeServ = traineeServ;
    }

    @GetMapping
    List<Trainee> listAll() {
        return traineeServ.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Trainee> displayTraineeDetails(@PathVariable int id) {
        log.info("Calling TraineeService's findTrainee passing ID: " + id);
        Trainee trainee = traineeServ.getTrainee(id);

        if(trainee != null) {
            log.info("Trainer found.");
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(trainee);
        }
        log.debug("Trainer not found");
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    @PostMapping
    ResponseEntity<Trainee> createTrainee(@RequestBody Trainee trainer) {
        Trainee traineeCreated = traineeServ.createTrainee(trainer);

        if(traineeCreated != null) {
            log.info("Trainee: " + traineeCreated + " created successfully.");
            URI loc = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .replacePath("api/v1/user/{id}")
                .buildAndExpand(traineeCreated.getId())
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
