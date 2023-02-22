package com.fdmgroup.attendancetracker.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.service.AttendanceService;

@RestController
@RequestMapping("api/v1/attendance")
@CrossOrigin(origins="http://localhost:3000")
public class AttendanceController {
    private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);

    private AttendanceService attendanceServ;
    
    public AttendanceController(AttendanceService attendanceServ) {
        this.attendanceServ = attendanceServ;
    }

    @GetMapping
    public List<Attendance> displayAllAttendances() {
        log.info("AttendanceController: displayAllAttendances - calling AttendanceService's listAll.");
        return attendanceServ.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> displayAttendanceDetails(@PathVariable int id) {
        log.info("AttendanceController: displayAttendanceDetails - displaying attendance details for attendance ID: " + id);
        Attendance attendance = attendanceServ.getAttendance(id);
        if(attendance != null) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(attendance);
        }

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    // @PostMapping
    // void createAttendance(@RequestBody Attendance attendance) {
    //     log.info("Trying to create: " + attendance);
    // }

    @PostMapping
    ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        Attendance attendanceCreated = attendanceServ.createAttendance(attendance);

        if(attendanceCreated != null) {
            log.info("Attendance: " + attendanceCreated + " created successfully.");

            URI loc = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(attendanceCreated.getId())
                .toUri();
            return ResponseEntity
                .created(loc)
                .build();
        }

        log.debug("Attendance not persisted.");
        return ResponseEntity   
            .status(HttpStatus.CONFLICT)
            .build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAttendance(@PathVariable int id) {
        if(attendanceServ.deleteAttendance(id)) {
            log.info("Attendance: " + id + " deleted successfully.");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        log.warn("Attendance: " + id + " not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
