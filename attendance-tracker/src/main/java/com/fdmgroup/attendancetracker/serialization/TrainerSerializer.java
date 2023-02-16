package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.attendancetracker.model.Trainer;

public class TrainerSerializer extends StdSerializer<Trainer> {
    public TrainerSerializer() {
        this(null);
    }

    public TrainerSerializer(Class<Trainer> t) {
        super(t);
    }

    @Override
    public void serialize(Trainer trainer, JsonGenerator gen, SerializerProvider provider) 
    throws IOException, JsonProcessingException {
        gen.writeStartObject();
            gen.writeNumberField("id", trainer.getId());
            gen.writeStringField("username", trainer.getUsername());
            gen.writeStringField("firstName", trainer.getFirstName());
            gen.writeStringField("lastName", trainer.getLastName());
            gen.writeStringField("password", trainer.getPassword());
            gen.writeStringField("currentCohort", trainer.getCurrentCohort().getCourseCode());
        gen.writeEndObject();
    }
}
