package com.fdmgroup.attendancetracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.attendancetracker.model.Cohort;
import com.fdmgroup.attendancetracker.service.CohortService;

@RestController
@RequestMapping("api/v1/cohort")
@CrossOrigin(origins = "http://localhost:3000")
public class CohortController {
    private static final Logger log = LoggerFactory.getLogger(CohortController.class);
    private CohortService cohortServ;

    public CohortController(CohortService cohortServ) {
        this.cohortServ = cohortServ;
    }

    @GetMapping
    public List<Cohort> displayAllCohorts() {
        log.info("CohortController: displayAllCohorts - calling cohortService's listAll");
        return cohortServ.listAll();
    }

    @GetMapping("/{courseCode}")
    public ResponseEntity<Cohort> displayCohortDetails(@PathVariable String courseCode) {
        log.info("CohortController: displayCohortDetails - calling CohortService's getCohort for cohort: " + courseCode);
        Cohort cohort = cohortServ.getCohort(courseCode);
        if(cohort != null) {
            log.info("CohortController: displayCohortDetails - Cohort found.");
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(cohort);
        }

        log.debug("CohortController: displayCohortDetails - Cohort Not Found.");
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    
}