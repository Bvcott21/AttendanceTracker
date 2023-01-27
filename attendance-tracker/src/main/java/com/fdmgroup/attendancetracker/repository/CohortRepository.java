package com.fdmgroup.attendancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.attendancetracker.model.Cohort;

public interface CohortRepository extends JpaRepository<Cohort, String> {
    
}
