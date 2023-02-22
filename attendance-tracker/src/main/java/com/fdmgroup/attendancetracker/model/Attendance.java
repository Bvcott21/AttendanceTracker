package com.fdmgroup.attendancetracker.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fdmgroup.attendancetracker.serialization.AttendanceSerializer;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data @Entity
@JsonSerialize(using = AttendanceSerializer.class)
public class Attendance {
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_gen")
    @SequenceGenerator(name = "attendance_gen", sequenceName = "attendance_seq", allocationSize = 1)
    private int id;

    @ManyToOne @JoinColumn(name = "FK_TRAINEE_ID")
    private Trainee trainee;
    private LocalDateTime trackTime;
    private Boolean isOnTime;
    private LocalDateTime approxArrivalTime;

    @Enumerated(EnumType.STRING)
    private AbsenceCategory absenceCategory;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "attendance_notes")
    private List<Note> notes = new ArrayList<Note>();

    @ManyToOne
    private User takenBy; 

    Attendance() {}

    public Attendance(Trainee trainee, LocalDateTime trackTime, Boolean isOnTime, LocalDateTime approxArrivalTime,
            AbsenceCategory absenceCategory, User takenBy) {
        this.trainee = trainee;
        this.trackTime = trackTime;
        this.isOnTime = isOnTime;
        this.approxArrivalTime = approxArrivalTime;
        this.absenceCategory = absenceCategory;
        this.takenBy = takenBy;
    }

    public Attendance(Trainee trainee, LocalDateTime trackTime, Boolean isOnTime, Trainer takenBy) {
        this.trainee = trainee;
        this.trackTime = trackTime;
        this.isOnTime = isOnTime;
        this.takenBy = takenBy;
    }

    public Attendance(Trainee trainee, LocalDateTime trackTime, Boolean isOnTime, Admin takenBy) {
        this.trainee = trainee;
        this.trackTime = trackTime;
        this.isOnTime = isOnTime;
        this.takenBy = takenBy;
    }

    public Attendance(Trainee trainee, Boolean isOnTime, Trainer takenBy) {
        this.trainee = trainee;
        this.trackTime = LocalDateTime.now();
        this.isOnTime = isOnTime;
        this.takenBy = takenBy;
    }

    public Attendance(Trainee trainee, Boolean isOnTime, Admin takenBy) {
        this.trainee = trainee;
        this.trackTime = LocalDateTime.now();
        this.isOnTime = isOnTime;
        this.takenBy = takenBy;
    }

    public void addNote(User user, String note) {
        notes.add(new Note(user, note));    
    }
    
}
