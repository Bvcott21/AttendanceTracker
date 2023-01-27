package com.fdmgroup.attendancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.attendancetracker.model.User;
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
