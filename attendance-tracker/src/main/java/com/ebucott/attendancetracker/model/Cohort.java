package com.ebucott.attendancetracker.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @Entity @NoArgsConstructor @RequiredArgsConstructor
public class Cohort {
    @NonNull @Id
    private String courseCode;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COURSE_CODE")
    private List<Trainee> trainees = new ArrayList<>();


}
