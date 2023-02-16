package com.fdmgroup.attendancetracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable @Data
public class Note {
    @Id
    @GeneratedValue
    private int id;
    @Column(updatable = false)
    @ManyToOne private User author;
    private LocalDateTime time;
    private String note;

    public Note() {}

    public Note(User author, String note) {
        this.author = author;
        time = LocalDateTime.now();
        this.note = note;
    }

}
