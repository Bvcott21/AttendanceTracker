package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.Admin;
import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.model.Trainer;
import com.fdmgroup.attendancetracker.model.User;
import com.fdmgroup.attendancetracker.repository.AttendanceRepository;
import com.fdmgroup.attendancetracker.repository.TraineeRepository;
import com.fdmgroup.attendancetracker.repository.UserRepository;

@Service
public class AttendanceService {
    private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);

    private AttendanceRepository attendanceRepo;
    private UserRepository userRepo;
    private TraineeRepository traineeRepo;

    

    public AttendanceService(AttendanceRepository attendanceRepo, UserRepository userRepo,
            TraineeRepository traineeRepo) {
        this.attendanceRepo = attendanceRepo;
        this.userRepo = userRepo;
        this.traineeRepo = traineeRepo;
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

    public Attendance createAttendance(Attendance attendance) {
        Trainee trainee = traineeRepo.findById(attendance.getTrainee().getId()).orElse(null);

        User takenBy = userRepo.findById(attendance.getTakenBy().getId()).orElse(null);

        if(takenBy instanceof Trainer) {
            attendance.setTakenBy((Trainer) takenBy);
        } 

        if(takenBy instanceof Admin) {
            attendance.setTakenBy((Admin) takenBy);
        }

        attendance.setTrainee(trainee);

        Attendance attendanceTaken = attendanceRepo.save(attendance);

        if (attendanceTaken == null) {
            log.debug("Attendance already exists.");
            return null;
        }

        log.info("Attendance persisted.");
        return attendanceTaken;
    }
    
}
