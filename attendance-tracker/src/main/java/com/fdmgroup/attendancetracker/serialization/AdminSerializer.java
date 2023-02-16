package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.attendancetracker.model.Admin;
import com.fdmgroup.attendancetracker.model.Cohort;

public class AdminSerializer extends StdSerializer<Admin> {
    public AdminSerializer() {
        this(null);
    }

    public AdminSerializer(Class<Admin> t) {
        super(t);
    }

    @Override
    public void serialize(Admin admin, JsonGenerator gen, SerializerProvider provider) 
    throws IOException, JsonProcessingException {
        gen.writeStartObject();
            
        gen.writeEndObject();
    }

}
