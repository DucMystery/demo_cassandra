package com.bezkoder.spring.data.cassandra.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomeSerializer extends StdSerializer<String> {
    public CustomeSerializer(){
        this(null);
    }
    public CustomeSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String address, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(new ObjectMapper().readTree(address));
    }
}
