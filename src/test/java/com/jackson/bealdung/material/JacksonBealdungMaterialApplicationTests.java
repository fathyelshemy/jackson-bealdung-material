package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.bealdung.material.dto.deserialize.User;
import com.jackson.bealdung.material.dto.serialize.ExtendedBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JacksonBealdungMaterialApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testJacksonSerializerOptions() throws JsonProcessingException, ParseException {

        Map<String,String> map= new HashMap<>();
        map.put("first","1");
        map.put("second","2");
        map.put("third","3");

        ExtendedBean extendedBean = ExtendedBean.builder()
                                        .id(1)
                                        .properties(map)
                                        .eventDate(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse("03-10-2020 06:41:39"))
                                        .build();

        String jacksonSerialize= new ObjectMapper().writeValueAsString(extendedBean);
        Assertions.assertEquals(jacksonSerialize,"{\"properties\":{\"third\":\"3\",\"first\":\"1\",\"second\":\"2\"},\"id\":1,\"eventDate\":\"03-10-2020 06:41:39\"}");
        Assertions.assertEquals(jacksonSerialize.substring(2,12),"properties");
        Assertions.assertEquals(jacksonSerialize.substring(54,56),"id");

    }


    @Test
    public void testJacksonDeserializerOptions() throws JsonProcessingException {

        String json= "{\"fname\":\"fathy\",\"l_name\":\"ahmed\",\"birthDate\":\"03-10-2020 06:41:39\"}";
        User user= new ObjectMapper().readerFor(User.class).readValue(json);
        Assertions.assertEquals(user.getFirstName(),"fathy");
        Assertions.assertEquals(user.getLastName(),"ahmed");

    }
}
