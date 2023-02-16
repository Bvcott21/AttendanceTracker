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
import com.fdmgroup.attendancetracker.service.AdminService;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins="http://localhost:3000")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private AdminService adminServ;
    
    AdminController(AdminService adminServ) {
        this.adminServ = adminServ;
    }

    @GetMapping
    List<Admin> listAll() {
        return adminServ.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Admin> displayAdminDetails(@PathVariable int id) {
        log.info("Calling AdminService's findAdmin passing ID: " + id);
        Admin admin = adminServ.getAdmin(id);

        if(admin != null) {
            log.info("Admin found.");
            return ResponseEntity  
                .status(HttpStatus.OK)
                .body(admin);
        }
        log.debug("Admin not found.");
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    @PostMapping
    ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin adminCreated = adminServ.createAdmin(admin);

        if(adminCreated != null) {
            URI loc = ServletUriComponentsBuilder
                .fromCurrentServletMapping() 
                .replacePath("api/v1/user/{id}")
                .buildAndExpand(adminCreated.getId())
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
