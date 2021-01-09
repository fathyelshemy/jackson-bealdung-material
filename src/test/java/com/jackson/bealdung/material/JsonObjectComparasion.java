package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.bealdung.material.dto.customComparator.NumericNodeComparator;
import com.jackson.bealdung.material.dto.customComparator.TextNodeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
 class JsonObjectComparasion {

    @Test
    void testCompareTwoJsonWithDifferentOrder() throws JsonProcessingException {
        String jsonStr1="{\"employee\":{\"id\": \"1212\",\"fullName\": \"John Miles\",\"age\": 34}}";
        String jsonStr2="{\"employee\":{\"id\": \"1212\",\"age\": 34,\"fullName\": \"John Miles\"}}";
        ObjectMapper json= new ObjectMapper();
        Assertions.assertEquals(json.readTree(jsonStr1),json.readTree(jsonStr2));
    }

    @Test
    void testCompareTwoNestedJsonObjectWithDifferentOrder() throws JsonProcessingException {
        String jsonStr1="{\"employee\":{\"id\": \"1212\",\"fullName\":\"John Miles\",\"age\": 34,\"contact\":{\"email\": \"john@xyz.com\",\"phone\": \"9999999999\"}}}";
        String jsonStr2="{\"employee\":{\"id\": \"1212\",\"age\": 34,\"fullName\": \"John Miles\",\"contact\":{\"email\": \"john@xyz.com\",\"phone\": \"9999999999\"}}}";
        ObjectMapper json= new ObjectMapper();
        Assertions.assertEquals(json.readTree(jsonStr1),json.readTree(jsonStr2));

    }

    @Test
    void  testCustomComparisonForDoubleValues() throws JsonProcessingException {
        String jsonStr1="{\"name\": \"John\",\"score\": 5.0}";
        String jsonStr2="{\"name\": \"John\",\"score\": 5}";
        ObjectMapper json= new ObjectMapper();
        Assertions.assertNotEquals(json.readTree(jsonStr1),json.readTree(jsonStr2));

        NumericNodeComparator nnc=new NumericNodeComparator();
        Assertions.assertTrue(json.readTree(jsonStr1).equals(nnc,json.readTree(jsonStr2)));
    }

    @Test
    void testCustomComparisonForTextIgnoreCaseValues() throws JsonProcessingException {
        String jsonStr1="{\"name\": \"JOHN\",\"score\": 5}";
        String jsonStr2="{\"name\": \"John\",\"score\": 5}";
        ObjectMapper json= new ObjectMapper();
        Assertions.assertNotEquals(json.readTree(jsonStr1),json.readTree(jsonStr2));

        TextNodeComparator nnc=new TextNodeComparator();
        Assertions.assertTrue(json.readTree(jsonStr1).equals(nnc,json.readTree(jsonStr2)));

    }
}
