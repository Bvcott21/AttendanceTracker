package com.fdmgroup.attendancetracker.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fdmgroup.attendancetracker.model.User;

public class UserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Serialize the User object to JSON
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("userType", user.getClass().getSimpleName());
        jsonGenerator.writeStringField("username", user.getUsername());
        jsonGenerator.writeStringField("firstName", user.getFirstName());
        jsonGenerator.writeStringField("lastName", user.getLastName());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("password", user.getPassword());
        // ... Add more fields as needed
        jsonGenerator.writeEndObject();
    }
}
