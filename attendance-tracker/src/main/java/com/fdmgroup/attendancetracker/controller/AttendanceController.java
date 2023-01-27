package com.fdmgroup.attendancetracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/edit-attendance")
    public String displayEditAttendance(Model model, @RequestParam("attendanceId") int attendanceId, @ModelAttribute Attendance attendance) {
        model.addAttribute("attendance", attendanceServ.getAttendance(attendanceId));
        return "editAttendance";
    }
    
}
