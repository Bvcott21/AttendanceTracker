package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.attendancetracker.model.Cohort;

public class CohortSerializer extends StdSerializer<Cohort> {
    public CohortSerializer() {
        this(null);
    }

    public CohortSerializer(Class<Cohort> t) {
        super(t);
    }

    @Override
    public void serialize(Cohort cohort, JsonGenerator gen, SerializerProvider provider) 
    throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("courseCode", cohort.getCourseCode());
            gen.writeFieldName("trainees");
            gen.writeStartArray();
                cohort
                    .getTrainees()
                    .forEach(trainee -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", trainee.getId()); 
                                gen.writeStringField("username", trainee.getUsername());
                                gen.writeStringField("firstName", trainee.getFirstName());
                                gen.writeStringField("lastName", trainee.getLastName());
                                gen.writeStringField("email", trainee.getEmail());
                                gen.writeStringField("DMSLinkInternal", trainee.getTraineeDMSLinkInternal());
                                gen.writeStringField("DMSLinkExternal", trainee.getTraineeDMSLinkExternal());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            gen.writeEndArray();
        gen.writeEndObject();
    }
    
    
}
