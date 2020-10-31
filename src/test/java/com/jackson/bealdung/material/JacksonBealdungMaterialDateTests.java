package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.bealdung.material.dto.date.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JacksonBealdungMaterialDateTests {

    // default jackson handler
    @Test
    public void defaultJacksonBehaviorInDate() throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date eventDate=sdf.parse("1970-01-01 01:00:00");

        Event event=Event.builder()
                        .eventDate(eventDate)
                        .eventName("new year Party")
                        .build();
        String result=new ObjectMapper().writeValueAsString(event);
        Map<String,String>map=new ObjectMapper().readValue(result, new TypeReference<Map<String,String>>(){});
        Assertions.assertEquals(map.get("eventName"),"new year Party");
        Assertions.assertEquals(Integer.valueOf(map.get("eventDate")),3600000);
    }

    

}
