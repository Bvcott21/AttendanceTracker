package com.fdmgroup.attendancetracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.service.AttendanceService;

@Controller
public class AttendanceController {
    private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);

    private AttendanceService attendanceServ;
    
    public AttendanceController(AttendanceService attendanceServ) {
        this.attendanceServ = attendanceServ;
    }

    @GetMapping("/show-attendance")
    public String displayAllAttendances(Model model) {
        log.info("AttendanceController: displayAllAttendances - calling AttendanceService's listAll.");
        List<Attendance> attendances = attendanceServ.listAll();
        
        log.info(attendances.size() + " records found.");
        log.info("AttendanceController: displayAllAttendances - injecting attendances retrieved into model.");
        model.addAttribute("attendances", attendances);

        log.info("AttendanceController: displayAllAttendances - sending user to showAttendance page.");
        return "showAttendance";
    }

    @GetMapping("/attendance-details")
    public String displayAttendanceDetails(Model model, @RequestParam("attendanceId") int attendanceId, @ModelAttribute Attendance attendance) {
        log.info("AttendanceController: displayAttendanceDetails - displaying attendance details for attendance ID: " + attendanceId);
        model.addAttribute("attendance", attendanceServ.getAttendance(attendanceId));
        return "attendanceDetails";
    }

    @GetMapping("/edit-attendance")
    public String displayEditAttendance(Model model, @RequestParam("attendanceId") int attendanceId, @ModelAttribute Attendance attendance) {
        model.addAttribute("attendance", attendanceServ.getAttendance(attendanceId));
        return "editAttendance";
    }
    
}
