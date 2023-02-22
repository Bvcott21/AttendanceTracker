package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fdmgroup.attendancetracker.model.Admin;
import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.model.Cohort;
import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.model.Trainer;
import com.fdmgroup.attendancetracker.model.User;
import com.fdmgroup.attendancetracker.repository.AttendanceRepository;
import com.fdmgroup.attendancetracker.repository.CohortRepository;
import com.fdmgroup.attendancetracker.repository.UserRepository;

public class UserDeserializer extends JsonDeserializer<User> {

    @Autowired private UserRepository userRepository;

    @Autowired private CohortRepository cohortRepository;

    @Autowired private AttendanceRepository attendanceRepository;

    @Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        User user;

        String userType = node.get("userType").asText();

        if (userType.equals("Trainer")) {
            user = new Trainer();
        } else if (userType.equals("Admin")) {
            user = new Admin();
        } else if (userType.equals("Trainee")) {
            user = new Trainee();
        } else {
            throw new IllegalArgumentException("Unknown user type: " + userType);
        }

        if (node.has("id")) {
            user.setId(node.get("id").asInt());
        }

        if (node.has("username")) {
            user.setUsername(node.get("username").asText());
        }

        if (node.has("email")) {
            user.setEmail(node.get("email").asText());
        }

        if (node.has("firstName")) {
            user.setFirstName(node.get("firstName").asText());
        }

        if (node.has("lastName")) {
            user.setLastName(node.get("lastName").asText());
        }

        if (node.has("password")) {
            user.setPassword(node.get("password").asText());
        }

        if (userType.equals("Trainer")) {
            if (node.has("currentCohort")) {
                String cohortId = node.get("currentCohort").asText();
                Cohort cohort = cohortRepository.findById(cohortId).orElse(null);
                ((Trainer) user).setCurrentCohort(cohort);
            }
        } else if (userType.equals("Trainee")) {
            if (node.has("traineeDMSLinkInternal")) {
                ((Trainee) user).setTraineeDMSLinkInternal(node.get("traineeDMSLinkInternal").asText());
            }
            if (node.has("traineeDMSLinkExternal")) {
                ((Trainee) user).setTraineeDMSLinkExternal(node.get("traineeDMSLinkExternal").asText());
            }
            if (node.has("cohort")) {
                String cohortId = node.get("currentCohort").asText();
                Cohort cohort = cohortRepository.findById(cohortId).orElse(null);
                ((Trainee) user).setCohort(cohort);
            }
            if (node.has("attendanceRecord")) {
                List<Attendance> attendanceRecord = new ArrayList<>();
                JsonNode attendanceRecordNode = node.get("attendanceRecord");
                if (attendanceRecordNode.isArray()) {
                    for (JsonNode attendanceNode : attendanceRecordNode) {
                        int attendanceId = attendanceNode.asInt();
                        Attendance attendance = attendanceRepository.findById(attendanceId).orElse(null);
                        attendanceRecord.add(attendance);
                    }
                }
                ((Trainee) user).setAttendanceRecord(attendanceRecord);
            }
        }

        return user;
    }
}