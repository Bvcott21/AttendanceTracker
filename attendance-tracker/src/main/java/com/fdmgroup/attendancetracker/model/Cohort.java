package com.fdmgroup.attendancetracker.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data @Entity
public class Cohort {
    @Id
    private String courseCode;
    
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COURSE_CODE")
    private List<Trainee> trainees = new ArrayList<>();

    Cohort() {}

    public Cohort(String courseCode) {
        this.courseCode = courseCode;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
        trainee.setCohort(this);
    }

    public void removeTrainee(Trainee trainee) {
        trainees.remove(trainee);
    }

}
