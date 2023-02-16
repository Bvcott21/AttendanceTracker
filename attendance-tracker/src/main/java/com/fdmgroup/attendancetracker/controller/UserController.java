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

import com.fdmgroup.attendancetracker.model.Admin;
import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.model.Trainer;
import com.fdmgroup.attendancetracker.model.User;
import com.fdmgroup.attendancetracker.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userServ;

    public UserController(UserService userServ) {
        this.userServ = userServ;
    }

    @GetMapping
    public List<User> displayAllUsers() {
        log.info("UserController: displayAllUsers: calling UserService's listAll");
        return userServ.listAll();
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<User> displayUserDetails(@PathVariable int id) {
        log.info("UserController: displayUserDetails - displaying User details for user ID: " + id);
        User user = userServ.getUser(id);
        if(user != null) {
            log.info("UserController: displayUserDetails - User found.");
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
        }

        log.debug("UserController: displayUserDetails - User not found.");
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userServ.createUser(user);

        if(userCreated != null) {
            log.info("User created.");
            URI loc = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
            return ResponseEntity
                .created(loc)
                .build();
        }
        log.debug("Cohort not persisted.");
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
    }

    // @PostMapping("/admin")
    // public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
    //     User adminCreated = userServ.createUser(admin);

    //     if(adminCreated != null) {
    //         URI loc = ServletUriComponentsBuilder
    //             .fromCurrentServletMapping() //Double check this
    //             .replacePath("api/v1/user/{id}")
    //             .buildAndExpand(adminCreated.getId())
    //             .toUri();

    //         System.out.println(loc);

    //         return ResponseEntity
    //             .created(loc)
    //             .build();
    //     }

    //     return ResponseEntity
    //         .status(HttpStatus.CONFLICT)
    //         .build();
    // }

    // @PostMapping("/trainer")
    // public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
    //     User trainerCreated = userServ.createUser(trainer);

    //     if(trainerCreated != null) {
    //         URI loc = ServletUriComponentsBuilder
    //             .fromCurrentServletMapping() //Double check this
    //             .replacePath("api/v1/user/{id}")
    //             .buildAndExpand(trainerCreated.getId())
    //             .toUri();

    //         System.out.println(loc);

    //         return ResponseEntity
    //             .created(loc)
    //             .build();
    //     }

    //     return ResponseEntity
    //         .status(HttpStatus.CONFLICT)
    //         .build();
    // }

    // @PostMapping("/trainee")
    // public ResponseEntity<Trainee> createTrainee(@RequestBody Trainee trainee) {
    //     User traineeCreated = userServ.createUser(trainee);

    //     if(traineeCreated != null) {
    //         URI loc = ServletUriComponentsBuilder
    //             .fromCurrentServletMapping() //Double check this
    //             .replacePath("api/v1/user/{id}")
    //             .buildAndExpand(traineeCreated.getId())
    //             .toUri();

    //         return ResponseEntity
    //             .created(loc)
    //             .build();
    //     }

    //     return ResponseEntity
    //         .status(HttpStatus.CONFLICT)
    //         .build();
    // }

    
}
