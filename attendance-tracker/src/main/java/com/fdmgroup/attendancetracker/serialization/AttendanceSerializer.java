package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.attendancetracker.model.Attendance;

public class AttendanceSerializer extends StdSerializer<Attendance> {
    public AttendanceSerializer() {
        this(null);
    }

    public AttendanceSerializer(Class<Attendance> t) {
        super(t);
    }

    @Override
    public void serialize(Attendance attendance, JsonGenerator gen, SerializerProvider provider) 
    throws IOException, JsonProcessingException {
        gen.writeStartObject();
            gen.writeNumberField("id", attendance.getId());
            gen.writeFieldName("trainee");
            gen.writeStartObject();
                gen.writeNumberField("id", attendance.getTrainee().getId());
                gen.writeStringField("username", attendance.getTrainee().getUsername());
                gen.writeStringField("firstName", attendance.getTrainee().getFirstName());
                gen.writeStringField("lastName", attendance.getTrainee().getLastName());
                gen.writeStringField("email", attendance.getTrainee().getEmail());
                gen.writeStringField("DMSLinkInternal", attendance.getTrainee().getTraineeDMSLinkInternal());
                gen.writeStringField("DMSLinkExternal", attendance.getTrainee().getTraineeDMSLinkExternal());
            gen.writeEndObject();

            gen.writeStringField("trackTime", attendance.getTrackTime().toString());
            gen.writeBooleanField("isOnTime", attendance.getIsOnTime());

            if(attendance.getIsOnTime() == false) {
                gen.writeStringField("approxArrivalTime", attendance.getApproxArrivalTime().toString());
                gen.writeStringField("AbsenceCategory", attendance.getAbsenceCategory().toString());
                //gen.writeStringField("notesOnAbsence", attendance.getNotesOnAbsence());
            }

            gen.writeFieldName("takenBy");
            gen.writeStartObject();
                gen.writeNumberField("id", attendance.getTakenBy().getId());
                gen.writeStringField("username", attendance.getTakenBy().getUsername());
                gen.writeStringField("firstName", attendance.getTakenBy().getFirstName());
                gen.writeStringField("lastName", attendance.getTakenBy().getLastName());
            gen.writeEndObject();

            gen.writeFieldName("notes");    
            gen.writeStartArray();
            if(attendance.getNotes() != null) {
                
                attendance
                    .getNotes()
                    .forEach(note -> {

                        try {
                            gen.writeStartObject();
                                gen.writeStringField("firstName", note.getAuthor().getFirstName());
                                gen.writeStringField("lastName", note.getAuthor().getLastName());
                                gen.writeStringField("note", note.getNote());
                                gen.writeStringField("time", note.getTime().toString());
                            gen.writeEndObject();
                        } catch(IOException e) {
                            e.printStackTrace();
                        } 
                    });
            }
            gen.writeEndArray();
        gen.writeEndObject();
    }

}
