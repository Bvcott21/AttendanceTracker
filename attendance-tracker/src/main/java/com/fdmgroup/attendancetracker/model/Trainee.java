package com.fdmgroup.attendancetracker.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @Entity @EqualsAndHashCode(callSuper = true)
public class Trainee extends User {
    private String traineeDMSLinkInternal;
    private String traineeDMSLinkExternal;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_TRAINEE_ID")
    private List<Attendance> attendanceRecord = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FK_COURSE_CODE")
    private Cohort cohort;

    Trainee() {}
    
    public Trainee(String username, String email, String firstName, String lastName, String password,
            String traineeDMSLinkInternal, String traineeDMSLinkExternal) {
        super(username, email, firstName, lastName, password);
        this.traineeDMSLinkInternal = traineeDMSLinkInternal;
        this.traineeDMSLinkExternal = traineeDMSLinkExternal;
    }

    public void addAttendanceToRecord(Attendance attendance) {
        attendanceRecord.add(attendance);
    }

    public void removeAttendanceFromRecord(Attendance attendance) {
        attendanceRecord.remove(attendance);
    }

    public String toString() {
        return super.toString() 
            + " DMS Internal Link: " 
            + this.getTraineeDMSLinkInternal() 
            + " - DMS External Link: "
            + this.getTraineeDMSLinkExternal()
            + " attendanceRecord: " 
            + attendanceRecord; 
    }
    
}
