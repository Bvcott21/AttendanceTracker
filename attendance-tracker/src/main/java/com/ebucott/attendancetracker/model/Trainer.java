package com.ebucott.attendancetracker.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity @Data @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class Trainer extends User {
    private Cohort currentCohort;

    public Trainer(@NonNull String username, @NonNull String email, @NonNull String firstName, @NonNull String lastName,
            @NonNull String password) {
        super(username, email, firstName, lastName, password);
    }

    

}
