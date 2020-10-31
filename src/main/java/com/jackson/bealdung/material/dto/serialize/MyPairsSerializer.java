package com.jackson.bealdung.material.dto.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

public class MyPairsSerializer extends JsonSerializer<MyPairs> {
    private ObjectMapper mapper= new ObjectMapper();
    @Override
    public void serialize(MyPairs myPairs, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        StringWriter writer= new StringWriter();
        mapper.writeValue(writer,myPairs);
        jsonGenerator.writeFieldName(myPairs.toString());
    }
}
