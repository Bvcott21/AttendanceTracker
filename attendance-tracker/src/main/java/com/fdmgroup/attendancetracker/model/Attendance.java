package com.fdmgroup.attendancetracker.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

@Data @Entity
public class Attendance {
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_gen")
    @SequenceGenerator(name = "attendance_gen", sequenceName = "attendance_seq", allocationSize = 1)
    private int id;

    @JsonManagedReference
    @ManyToOne @JoinColumn(name = "FK_TRAINEE_ID")
    private Trainee trainee;
    private LocalDateTime trackTime;
    private Boolean isOnTime;
    private LocalDateTime approxArrivalTime;

    @Enumerated(EnumType.STRING)
    private AbsenceCategory absenceCategory;
    //Pensar en hacerlo una lista
    private String notesOnAbsence;

    @JsonManagedReference
    @ManyToOne
    private User takenBy; 

    Attendance() {}

    public Attendance(Trainee trainee, LocalDateTime trackTime, Boolean isOnTime, LocalDateTime approxArrivalTime,
            AbsenceCategory absenceCategory, String notesOnAbsence, User takenBy) {
        this.trainee = trainee;
        this.trackTime = trackTime;
        this.isOnTime = isOnTime;
        this.approxArrivalTime = approxArrivalTime;
        this.absenceCategory = absenceCategory;
        this.notesOnAbsence = notesOnAbsence;
        this.takenBy = takenBy;
    }

    @Override
    public String toString() {
        return id
        + " - Trainee: "
        + trainee.getFirstName() + " " + trainee.getLastName()
        + " - trackTime: "
        + trackTime
        + " - isOnTime: "
        + isOnTime
        + " - approxArrivalTime: "
        + approxArrivalTime
        + " - absenceCategory"
        + absenceCategory
        + " - notes on absence: " 
        + notesOnAbsence
        + " - takenBy: "
        + takenBy.getFirstName() + ' ' + takenBy.getLastName();
    }

    
}
