package com.fdmgroup.attendancetracker.repository;

import com.fdmgroup.attendancetracker.model.Admin;

import jakarta.transaction.Transactional;

@Transactional
public interface AdminRepository extends UserBaseRepository<Admin>{}
