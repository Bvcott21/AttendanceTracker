package com.fdmgroup.attendancetracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.attendancetracker.model.User;

public interface UserBaseRepository<T extends User> extends JpaRepository<T, Integer> {}
