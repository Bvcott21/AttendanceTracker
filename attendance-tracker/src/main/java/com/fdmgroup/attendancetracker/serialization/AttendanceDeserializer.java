package com.fdmgroup.attendancetracker.serialization;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fdmgroup.attendancetracker.model.AbsenceCategory;
import com.fdmgroup.attendancetracker.model.Attendance;
import com.fdmgroup.attendancetracker.model.Note;
import com.fdmgroup.attendancetracker.model.Trainee;
import com.fdmgroup.attendancetracker.model.User;
import com.fdmgroup.attendancetracker.repository.TraineeRepository;
import com.fdmgroup.attendancetracker.repository.UserRepository;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.IOException;

public class AttendanceDeserializer extends StdDeserializer<Attendance> {

    @Autowired private TraineeRepository traineeRepository;

    @Autowired private UserRepository userRepository;

    public AttendanceDeserializer() {
        this(null);
    }

    public AttendanceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Attendance deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        Attendance attendance = new Attendance();

        if (node.has("id")) {
            attendance.setId(node.get("id").asInt());
        }

        if (node.has("trainee")) {
            JsonNode traineeNode = node.get("trainee");
            if (traineeNode.has("id")) {
                Trainee trainee = traineeRepository.findById(traineeNode.get("id").asInt()).get();
                attendance.setTrainee(trainee);
            }
        }

        if (node.has("trackTime")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime trackTime = LocalDateTime.parse(node.get("trackTime").asText(), formatter);
            attendance.setTrackTime(trackTime);
        }

        if (node.has("isOnTime")) {
            attendance.setIsOnTime(node.get("isOnTime").asBoolean());
        }

        if (node.has("approxArrivalTime")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime approxArrivalTime = LocalDateTime.parse(node.get("approxArrivalTime").asText(), formatter);
            attendance.setApproxArrivalTime(approxArrivalTime);
        }

        if (node.has("absenceCategory")) {
            String absenceCategoryString = node.get("absenceCategory").asText();
            AbsenceCategory absenceCategory = AbsenceCategory.valueOf(absenceCategoryString);
            attendance.setAbsenceCategory(absenceCategory);
        }

        if (node.has("takenBy")) {
            JsonNode takenByNode = node.get("takenBy");
            if (takenByNode.has("id")) {
                User takenBy = userRepository.findById(takenByNode.get("id").asInt()).get();
                attendance.setTakenBy(takenBy);
            }
        }

        if (node.has("notes")) {
            List<Note> notes = new ArrayList<>();
            JsonNode notesNode = node.get("notes");
            if (notesNode.isArray()) {
                for (JsonNode noteNode : notesNode) {
                    if (noteNode.has("firstName") && noteNode.has("lastName") && noteNode.has("note") && noteNode.has("time")) {
                        Note note = new Note();
                        note.setAuthor(userRepository.findById(noteNode.get("id").asInt()).get());
                        note.setNote(noteNode.get("note").asText());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime time = LocalDateTime.parse(noteNode.get("time").asText(), formatter);
                        note.setTime(time);
                        notes.add(note);
                    }
                }
            }
            attendance.setNotes(notes);
        }

        return attendance;
    }
}
