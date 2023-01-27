package com.fdmgroup.attendancetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity @Data @EqualsAndHashCode(callSuper = true)
public class Trainer extends User {
    @ManyToOne @JoinColumn(name = "FK_COURSE_CODE")
     private Cohort currentCohort;

    public Trainer() {}

    public Trainer(String username, String email, String firstName, String lastName, String password) {
        super(username, email, firstName, lastName, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
     
}
