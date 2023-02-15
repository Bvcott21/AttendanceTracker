package com.fdmgroup.attendancetracker.model;

import io.micrometer.common.lang.NonNull;
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

@Entity @Data 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
@Table(name = "FDM_USER")
public abstract class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "fdm_user_gen")
    @SequenceGenerator(name = "fdm_user_gen", sequenceName = "fdm_user_seq", allocationSize = 1)
    private int id;

    @NonNull @Column(nullable=false, unique=true)
    private String username;

    @NonNull @Column(nullable=false, unique=true)
    private String email;

    @NonNull @Column(nullable=false, unique=true)
    private String firstName;

    @NonNull @Column(nullable=false)
    private String lastName;

    @NonNull @Column(nullable=false)
    private String password;

    User() {}

    protected User(String username, String email, String firstName, String lastName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    

}
