package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.bealdung.material.dto.customdeserializer.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomDeserializerTests {

    @Test
    void testItemCustomDeserializer() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        String itemTest="{\"id\": 1,\"itemName\": \"theItem\",\"createdBy\": 2}";
        Item item=objectMapper.readValue(itemTest,Item.class);
        Assertions.assertEquals(item.getId(),1);
        Assertions.assertEquals(item.getItemName(),"theItem");
        Assertions.assertEquals(item.getOwner().getId(),2);
        Assertions.assertNull(item.getOwner().getUserName());
    }
}
