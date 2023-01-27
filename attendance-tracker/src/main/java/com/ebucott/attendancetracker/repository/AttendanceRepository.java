package com.ebucott.attendancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebucott.attendancetracker.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    
}
