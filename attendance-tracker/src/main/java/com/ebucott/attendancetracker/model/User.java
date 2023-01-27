package com.ebucott.attendancetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity @Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="FDM_USER")
@NoArgsConstructor @RequiredArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FDM_USER_GEN")
    @SequenceGenerator(name = "FDM_USER_GEN", sequenceName = "FDM_USER_SEQ", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String username;

    @Column(nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String firstName;

    @Column(nullable = false)
    @NonNull
    private String lastName;

    @Column(nullable = false)
    @NonNull
    private String password;    
    
}
