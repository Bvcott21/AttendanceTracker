package com.fdmgroup.attendancetracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fdmgroup.attendancetracker.model.Cohort;
import com.fdmgroup.attendancetracker.repository.CohortRepository;

@Service
public class CohortService {
    private static final Logger log = LoggerFactory.getLogger(CohortService.class);
    
    private CohortRepository cohortRepo;

    public CohortService(CohortRepository cohortRepo) {
        this.cohortRepo = cohortRepo;
    }

    public Cohort getCohort(String courseCode) {
        log.info("CohortService: getCohort = Calling CohortRepository's - findById with ID: " + courseCode);
        Optional<Cohort> optCohort = cohortRepo.findById(courseCode);
        
        if(optCohort.isPresent()) {
            log.info("CohortService: getCohort - Match found.");
            return optCohort.get();
        }

        log.debug("CohortService: getCohort - Not Found.");
        return null;
    }

    public List<Cohort> listAll() {
        log.info("CohortService: listAll - calling cohortRepository's findAll");
        return cohortRepo.findAll();
    }

    public Cohort createCohort(Cohort cohort) {
        Optional<Cohort> existingCohort = cohortRepo.findById(cohort.getCourseCode());
        
        if(existingCohort.isEmpty()) {
            log.info("Cohort isn't in database. Persisting...");
            return cohortRepo.save(cohort);
        }
        log.debug("Cohort already in database.");
        return null;
    }

    public boolean deleteCohort(String courseCode) {
        if(cohortRepo.existsById(courseCode)) {
            cohortRepo.deleteById(courseCode);
            return true;
        }
        return false;
    }
}
