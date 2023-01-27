package com.fdmgroup.attendancetracker.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity @Data @EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    public Admin(String username, String email, String firstName, String lastName, String password) {
        super(username, email, firstName, lastName, password);
    }

    Admin() {}

    @Override
    public String toString() {
        return super.toString(); 
    }

}
