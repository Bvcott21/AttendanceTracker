package com.fdmgroup.attendancetracker.repository;

import com.fdmgroup.attendancetracker.model.Trainer;

import jakarta.transaction.Transactional;

@Transactional
public interface TrainerRepository extends UserBaseRepository<Trainer>{}
