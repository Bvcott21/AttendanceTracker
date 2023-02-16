package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.attendancetracker.model.Trainee;

public class TraineeSerializer extends StdSerializer<Trainee> {
    public TraineeSerializer() {
        this(null);
    }

    public TraineeSerializer(Class<Trainee> t) {
        super(t);
    }

    @Override
    public void serialize(Trainee t, JsonGenerator gen, SerializerProvider provider) 
    throws IOException, JsonProcessingException {
        gen.writeStartObject();
            gen.writeNumberField("id", t.getId());
            gen.writeStringField("username", t.getUsername());
            gen.writeStringField("firstName", t.getFirstName());
            gen.writeStringField("lastName", t.getLastName());
            gen.writeStringField("email", t.getEmail());
            gen.writeStringField("password", t.getPassword());
            gen.writeStringField("cohort", t.getCohort().getCourseCode());
            gen.writeStringField("DMSLinkInternal", t.getTraineeDMSLinkInternal());
            gen.writeStringField("DMSLinkExternal", t.getTraineeDMSLinkExternal());
            gen.writeFieldName("attendanceRecord");
            gen.writeStartArray();
                t
                    .getAttendanceRecord()
                    .forEach(attendance -> {
                        try{
                            gen.writeStartObject();
                                gen.writeNumberField("id", attendance.getId());
                                gen.writeStringField("trackTime", attendance.getTrackTime().toString());
                                gen.writeBooleanField("isOnTime", attendance.getIsOnTime());
                                if(!attendance.getIsOnTime()) {
                                    gen.writeStringField("approxArrivalTime", attendance.getApproxArrivalTime().toString());
                                    gen.writeStringField("absenceCategory", attendance.getAbsenceCategory().toString());
                                }
                                gen.writeStringField("takenBy", attendance.getTakenBy().getUsername());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            gen.writeEndArray();
        gen.writeEndObject();
    }
    
}
