package com.ebucott.attendancetracker.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity @Data @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    
    public Admin(@NonNull String username, @NonNull String email, @NonNull String firstName, @NonNull String lastName,
            @NonNull String password) {
        super(username, email, firstName, lastName, password);
    }
    
}
