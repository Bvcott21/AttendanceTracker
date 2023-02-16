package com.fdmgroup.attendancetracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
