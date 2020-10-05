package com.jackson.bealdung.material.dto.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateEventSerializer extends StdSerializer<Date> {

    private  SimpleDateFormat stf= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public DateEventSerializer(){
        this(null);
    }
    public DateEventSerializer(Class<Date>t){
        super(t);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeString(stf.format(date).toString());
    }
}
