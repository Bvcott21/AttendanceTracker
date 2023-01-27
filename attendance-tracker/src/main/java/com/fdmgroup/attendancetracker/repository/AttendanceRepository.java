package com.fdmgroup.attendancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.attendancetracker.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
    
}
