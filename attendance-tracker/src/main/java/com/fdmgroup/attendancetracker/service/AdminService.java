package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.Admin;
import com.fdmgroup.attendancetracker.repository.AdminRepository;

@Service
public class AdminService {
    private AdminRepository adminRepo;
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public List<Admin> findAll() {
        return adminRepo.findAll();
    }

    public Admin createAdmin(Admin admin) {
        Admin adminTaken = adminRepo.save(admin);

        if(adminTaken == null) {
            log.debug("Admin already exists.");
            return null;
        }

        log.info("Admin persisted.");
        return adminTaken;
    }

    public Admin getAdmin(int id) {
        log.info("Calling AdminRepo's findById with ID: " + id);
        Optional<Admin> optAdmin = adminRepo.findById(id);

        if(optAdmin.isPresent()) {
            log.info("Match found.");
            return optAdmin.get();
        }

        log.debug("Not found.");
        return null;
    }
}
