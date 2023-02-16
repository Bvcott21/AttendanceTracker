package com.fdmgroup.attendancetracker.repository;

import com.fdmgroup.attendancetracker.model.User;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {}
