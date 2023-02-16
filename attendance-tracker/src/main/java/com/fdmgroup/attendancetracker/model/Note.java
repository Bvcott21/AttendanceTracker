package com.fdmgroup.attendancetracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable @Data
public class Note {
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
