package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.repository.AttendanceRepository;

@Service
public class AttendanceService {
    private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);

    private AttendanceRepository attendanceRepo;

    public AttendanceService(AttendanceRepository attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public List<Attendance> listAll() {
        log.info("AttendaceService: listAll - Calling AttendanceRepository's findAll");
        return attendanceRepo.findAll();
    }

    public Attendance getAttendance(int id) {
        log.info("AttendaceService: getAttendance - Calling AttendanceRepository's findById with ID: " + id);
        Optional<Attendance> optionalAttendance = attendanceRepo.findById(id);

        if(optionalAttendance.isPresent()) {
            log.info("AttendaceService: getAttendance - Match Found");
            return optionalAttendance.get();
        }

        log.debug("AttendaceService: getAttendance - Not Found");
        return null;
    }
    
}
