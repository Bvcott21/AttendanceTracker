package com.ebucott.attendancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebucott.attendancetracker.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
