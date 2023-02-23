package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.User;
import com.fdmgroup.attendancetracker.repository.UserRepository;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser(int id) {
        log.info("UserService: getUser - Calling UserRepository's findById with ID: " + id);
        Optional<User> optUser = userRepo.findById(id);

        if(optUser.isPresent()) {
            log.info("UserService: getUser - Match Found");
            return optUser.get();
        }

        log.debug("UserService: getUser - Not Found.");
        return null;
    }

    public List<User> listAll() {
        log.info("UserService: listCall - calling userRepository's findAll");
        return userRepo.findAll();
        
    }

    public User createUser(User user) {
        User userTaken = userRepo.save(user);

        if(userTaken == null) {
            log.debug("User already exists.");
            return null;
        }

        log.info("User persisted.");
        return userTaken;
    }

    public boolean deleteUser(int id) {
        if(userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    
}
