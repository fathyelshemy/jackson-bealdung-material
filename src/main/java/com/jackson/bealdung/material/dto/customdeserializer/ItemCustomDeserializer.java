package com.jackson.bealdung.material.dto.customdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;


public class ItemCustomDeserializer  extends StdDeserializer<Item> {

    protected ItemCustomDeserializer() {
        this(null);
    }

    @Override
    public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode=jsonParser.getCodec().readTree(jsonParser);
        int id=jsonNode.get("id").asInt();
        String itemName= jsonNode.get("itemName").asText();
        int userId=  jsonNode.get("createdBy").asInt();

        return new Item(id,itemName,new User(userId,null));
    }

    protected ItemCustomDeserializer(Class<Item> t) {
        super(t);
    }
}
