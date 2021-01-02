package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.jackson.bealdung.material.dto.jsonviews.Item;
import com.jackson.bealdung.material.dto.jsonviews.User;
import com.jackson.bealdung.material.dto.jsonviews.Views;
import static org.junit.jupiter.api.Assertions.*;

import com.jackson.bealdung.material.dto.jsonviews.customjsonviews.MyBeanSerializerModifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
*  used to test logic Json View for Bealdung
* @url: https://www.baeldung.com/jackson-json-view-annotation#use-multiple-json-views
*/
@SpringBootTest
@ExtendWith(SpringExtension.class)
 class JacksonBealdungMaterialJsonViewsTests {


    //Serialized with views.public
    @Test
    void testUserParentViewsPublic() throws JsonProcessingException {

        User user= new User(1,"Fathy Elshemy");
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        String userSerialized= objectMapper
                            .writerWithView(Views.Public.class)
                            .writeValueAsString(user);
        assertTrue(userSerialized.contains("Fathy Elshemy"));
        assertFalse(userSerialized.contains("1"));
    }

    // to test parent view by inhertance
    @Test
    void testItemParentViewsPublicInternal() throws JsonProcessingException {

        Item item= new Item(1,"clean coder","fathy");
        ObjectMapper objectMapper= new ObjectMapper();
        String ItemSerialized= objectMapper
                        .writerWithView(Views.Public.class)
                        .writeValueAsString(item);

        assertTrue(ItemSerialized.contains("1"));
        assertTrue(ItemSerialized.contains("clean coder"));
        assertFalse(ItemSerialized.contains("fathy"));

    }
    // to test Child view by inhertance
    @Test
    void testItemChildViewsPublicInternal() throws JsonProcessingException {

        Item item= new Item(1,"clean coder","fathy");
        ObjectMapper objectMapper= new ObjectMapper();
        String ItemSerialized= objectMapper
                        .writerWithView(Views.Internal.class)
                        .writeValueAsString(item);

        assertTrue(ItemSerialized.contains("fathy"));
        assertTrue(ItemSerialized.contains("1"));
        assertTrue(ItemSerialized.contains("clean coder"));
    }


    @Test
    void testCustomViewSerializer() throws JsonProcessingException {
        User user= new User(1,"fathy");
        SerializerFactory serializerFactory= BeanSerializerFactory.instance
                                                        .withSerializerModifier(new MyBeanSerializerModifier());
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.setSerializerFactory(serializerFactory);

        String customSerializer=objectMapper.writerWithView(Views.Public.class)
                                            .writeValueAsString(user);
        assertTrue(customSerializer.contains("FATHY"));
    }
}
