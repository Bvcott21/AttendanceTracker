package com.fdmgroup.attendancetracker;

import java.time.LocalDateTime;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.attendancetracker.model.AbsenceCategory;
import com.fdmgroup.attendancetracker.model.Admin;
import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.model.Cohort;
import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.model.Trainer;
import com.fdmgroup.attendancetracker.repository.AttendanceRepository;
import com.fdmgroup.attendancetracker.repository.CohortRepository;
import com.fdmgroup.attendancetracker.repository.UserRepository;



@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, CohortRepository cohortRepo, AttendanceRepository attendanceRepo) {
        return args -> {
            //Creating cohorts
            Cohort cohort1 = new Cohort("COURSE_CODE_1");
            Cohort cohort2 = new Cohort("COURSE_CODE_2");

            //Persisting cohorts
            cohortRepo.save(cohort1);
            cohortRepo.save(cohort2);

            //Creating trainers
            Trainer trainer1 = new Trainer("Edgar.Afonso", "edgar.afonso@fdmgroup.com", "Edgar",  "Afonso", "Apple123");
            Trainer trainer2 = new Trainer("Nick.Lawton", "nick.lawton@fdmgroup.com", "Nick",  "Lawton", "Pear45678");

            //Creating Trainees
            Trainee trainee1 = new Trainee("John.Doe", "john.doe@fdmgroup.com", "John", "Doe", "P4ssword*", "www.trainee-internal-dms1.com", "www.trainee-external-dms1.com");
            Trainee trainee2 = new Trainee("Jane.MacAllister", "jane.macallister@fdmgroup.com", "Jane", "MacAllister", "P4ssword2*", "www.trainee-internal-dms2.com", "www.trainee-external-dms2.com");
            Trainee trainee3 = new Trainee("Howard.Griffin", "howard.griffin@fdmgroup.com", "Howard", "Griffin", "P4ssword4", "www.trainee-internal-dms3.com", "www.trainee-external-dms3.com");
            Trainee trainee4 = new Trainee("Carol.Johnson", "carol.johnson", "Carol", "Johnson", "P4ssword5*", "www.trainee-internal-dms4.com", "www.trainee-external-dms4.com");

            //TO-DO Create a couple of Admins, a couple more trainees and make attendances from Admins on the newly created trainees
            Admin admin1 = new Admin("Christine.Kelly", "christine.kelly@fdmgroup.com", "Christine",  "Kelly", "P4ssword123");
            Admin admin2 = new Admin("Jack.Smith", "jack.smith@fdmgroup.com", "Jack",  "Smith", "P4ssword123123");

            //Assigning cohorts to trainers and trainees
            trainer1.setCurrentCohort(cohort1);
            trainer2.setCurrentCohort(cohort2);

            cohort1.addTrainee(trainee1);
            cohort1.addTrainee(trainee2);
            
            cohort2.addTrainee(trainee3);
            cohort2.addTrainee(trainee4);

            //Persisting trainees
            userRepo.save(trainee1);
            userRepo.save(trainee2);
            userRepo.save(trainee3);
            userRepo.save(trainee4);
            
            
            
            //Persisting trainers
            userRepo.save(trainer1);
            userRepo.save(trainer2);

            //Persisting admins
            userRepo.save(admin1);
            userRepo.save(admin2);

            

            //Creating AttendanceReports
            Attendance attendance1 = new Attendance(trainee1, LocalDateTime.of(2022, Month.JULY, 15, 9, 15, 15), true, LocalDateTime.of(2022, Month.JULY, 20, 9, 0, 0), null, null, trainer1);
            Attendance attendance2 = new Attendance(trainee1, LocalDateTime.of(2022, Month.JULY, 20, 9, 15, 15), false, LocalDateTime.of(2022, Month.JULY, 20, 10, 0, 0), AbsenceCategory.LATE, "Mentioned presenting technical issues", trainer1);
            
            Attendance attendance3 = new Attendance(trainee2, LocalDateTime.of(2022, Month.JULY, 15, 9, 15, 15), true, LocalDateTime.of(2022, Month.JULY, 20, 9, 0, 0), null, null, trainer2);
            Attendance attendance4 = new Attendance(trainee2, LocalDateTime.of(2022, Month.JULY, 20, 9, 15, 15), false, LocalDateTime.of(2022, Month.JULY, 20, 12, 0, 0), AbsenceCategory.SICKNESS, "mentioned feeling unwell", trainer2);
            
            Attendance attendance5 = new Attendance(trainee3, LocalDateTime.of(2022, Month.JULY, 20, 9, 15, 15), false, LocalDateTime.of(2022, Month.JULY, 20, 12, 0, 0), AbsenceCategory.SICKNESS, "mentioned feeling unwell", admin1);
            Attendance attendance6 = new Attendance(trainee3, LocalDateTime.of(2022, Month.JULY, 20, 9, 15, 15), false, LocalDateTime.of(2022, Month.JULY, 20, 12, 0, 0), AbsenceCategory.SICKNESS, "mentioned feeling unwell", admin1);

            Attendance attendance7 = new Attendance(trainee4, LocalDateTime.of(2022, Month.JULY, 20, 9, 15, 15), false, LocalDateTime.of(2022, Month.JULY, 20, 12, 0, 0), AbsenceCategory.SICKNESS, "mentioned feeling unwell", admin2);
            Attendance attendance8 = new Attendance(trainee4, LocalDateTime.of(2022, Month.JULY, 20, 9, 15, 15), false, LocalDateTime.of(2022, Month.JULY, 20, 12, 0, 0), AbsenceCategory.SICKNESS, "mentioned feeling unwell", admin2);
            
            //Adding attendance to trainee
            trainee1.addAttendanceToRecord(attendance1);
            trainee1.addAttendanceToRecord(attendance2);

            trainee2.addAttendanceToRecord(attendance3);
            trainee2.addAttendanceToRecord(attendance4);

            trainee3.addAttendanceToRecord(attendance5);
            trainee3.addAttendanceToRecord(attendance6);

            trainee4.addAttendanceToRecord(attendance7);
            trainee4.addAttendanceToRecord(attendance8);


            //Persisting attendance
            attendanceRepo.save(attendance1);
            attendanceRepo.save(attendance2);
            attendanceRepo.save(attendance3);
            attendanceRepo.save(attendance4);
            attendanceRepo.save(attendance5);
            attendanceRepo.save(attendance6);
            attendanceRepo.save(attendance7);
            attendanceRepo.save(attendance8);

                System.out.println("=======================================================");
                System.out.println("==================Preloading Cohort====================");
                System.out.println("=======================================================");
            cohortRepo
                .findAll()
                .forEach(cohort -> {
                    log.info("Preloaded Cohort: " + cohort);
                    System.out.println("=======================================================");
                });

                System.out.println("=======================================================");
                System.out.println("===================Preloading Users====================");
                System.out.println("=======================================================");
            userRepo
                .findAll()
                    .forEach(user -> {
                    log.info("Preloaded User: " + user);
                    System.out.println("=======================================================");
                });

                System.out.println("=======================================================");
                System.out.println("==============Preloading Attendance=======================");
                System.out.println("=======================================================");

            attendanceRepo
                .findAll()
                .forEach(attendance -> {
                    log.info("Preloaded Attendance: " + attendance);
                    System.out.println("=======================================================");
                });
        };
    }
}
