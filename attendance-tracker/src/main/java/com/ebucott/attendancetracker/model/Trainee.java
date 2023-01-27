package com.ebucott.attendancetracker.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity @Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public class Trainee extends User {
    private String traineeDMSLinkInternal;
    private String traineeDMSLinkExternal;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "FK_TRAINEE_ID")
    private List<Attendance> attendanceRecord = new ArrayList<>();

    public Trainee(@NonNull String username, @NonNull String email, @NonNull String firstName, @NonNull String lastName,
            @NonNull String password, String traineeDMSLinkInternal, String traineeDMSLinkExternal) {
        super(username, email, firstName, lastName, password);
        this.traineeDMSLinkInternal = traineeDMSLinkInternal;
        this.traineeDMSLinkExternal = traineeDMSLinkExternal;
    }

    
}
