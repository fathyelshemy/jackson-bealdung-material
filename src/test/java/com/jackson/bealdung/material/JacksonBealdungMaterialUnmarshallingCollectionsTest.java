package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.bealdung.material.dto.objectMapperex.Car;
import org.assertj.core.util.Lists;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.instanceOf;

import java.util.List;

/*
 *  used to test logic Json View for Bealdung
 * @url: https://www.baeldung.com/jackson-collection-array
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class JacksonBealdungMaterialUnmarshallingCollectionsTest {

    ObjectMapper objectMapper;

    List<Car> cars;
    @BeforeEach
    void setup() {
        this.cars= Lists.newArrayList(
                new Car("RED","BMW"),
                new Car("BLUE","GELY")
        );
        this.objectMapper= new ObjectMapper();
    }
    @Test
    void testDeserializeStandardArrayByJackson() throws JsonProcessingException {

        String carsJsonStr=objectMapper.writeValueAsString(cars);
        //[{"color":"RED","type":"BMW"},{"color":"BLUE","type":"GELY"}]

        Car[] carsArray= objectMapper.readValue(carsJsonStr,Car[].class);
        MatcherAssert.assertThat(carsArray[0], instanceOf(Car.class));
    }

    @Test
    void testDeserializeCollectionByJackson() throws JsonProcessingException {
        String carsJsonStr=objectMapper.writeValueAsString(cars);
        List<Car>carList=objectMapper.readValue(carsJsonStr, new TypeReference<List<Car>>() { });

        MatcherAssert.assertThat(carList.get(0), instanceOf(Car.class));


    }
}
