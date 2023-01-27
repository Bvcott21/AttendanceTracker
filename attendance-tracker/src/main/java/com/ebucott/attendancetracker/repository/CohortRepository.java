package com.ebucott.attendancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebucott.attendancetracker.model.Cohort;

public interface CohortRepository extends JpaRepository<Cohort, String> {
    
}
