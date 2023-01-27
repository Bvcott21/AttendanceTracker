package com.ebucott.attendancetracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @Entity @RequiredArgsConstructor @NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACADEMY_GEN")
    @SequenceGenerator(name = "ACADEMY_GEN", sequenceName = "ACADEMY_SEQ", allocationSize = 1)
    private int id;

    @ManyToOne @JoinColumn(name = "FK_TRAINEE_ID") @NonNull
    private Trainee trainee;

    @Column(nullable = false) @NonNull
    private LocalDateTime trackTime;

    @Column(nullable = false) @NonNull
    private Boolean isOnTime;
    
    @Enumerated(EnumType.STRING)
    private AbsenceCategory category;
    private String notesOnAbsence;
    
    @ManyToOne 
    @Column(nullable = false)
    private User takenBy;
    
}
