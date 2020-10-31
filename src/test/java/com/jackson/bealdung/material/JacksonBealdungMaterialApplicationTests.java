package com.jackson.bealdung.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.jackson.bealdung.material.dto.JacksonPolymrophic.Animal;
import com.jackson.bealdung.material.dto.JacksonPolymrophic.Cat;
import com.jackson.bealdung.material.dto.JacksonPolymrophic.Dog;
import com.jackson.bealdung.material.dto.deserialize.User;
import com.jackson.bealdung.material.dto.objectMapperex.Car;
import com.jackson.bealdung.material.dto.objectMapperex.CarIgnoreUnkown;
import com.jackson.bealdung.material.dto.serialize.ExtendedBean;
import com.jackson.bealdung.material.dto.serialize.MyPairs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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

    @Test
    public void testJacksonPolymorphicByCat() throws JsonProcessingException {
        String jsonCat="{\"type\":\"cat\",\"name\":\"lacy\"}";
        Cat cat= new ObjectMapper().readerFor(Animal.class).readValue(jsonCat);
        Assertions.assertEquals(cat.getName(),"lacy");
    }
    @Test
    public void testJacksonPolymorphicByDog() throws JsonProcessingException {
        String jsonDog="{\"type\":\"dog\",\"name\":\"lacy\",\"sound\":0}";
        Dog dog= new ObjectMapper().readValue(jsonDog,Dog.class);
        Assertions.assertEquals(dog.getName(),"lacy");
    }

    @Test
    public void testObjectMapperWithSerialize() throws JsonProcessingException {
        Car car= new Car("red","BMW");
        String jsonCar=new ObjectMapper().writeValueAsString(car);
        Assertions.assertEquals(jsonCar,"{\"color\":\"red\",\"type\":\"BMW\"}");
    }

    @Test
    public void testObjectMapperWithDeserialize() throws JsonProcessingException {
        String jsonCar="{\"color\":\"black\",\"type\":\"Mercedes\"}";
        Car car= new ObjectMapper().readerFor(Car.class).readValue(jsonCar);

        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getColor(),"black");
        Assertions.assertEquals(car.getType(),"Mercedes");
    }

    @Test
    public void testObjectMapperWithJsonNode() throws JsonProcessingException {
        String jsonCar="{\"color\":\"black\",\"type\":\"Mercedes\"}";
        JsonNode car=new ObjectMapper().readTree(jsonCar);

        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.get("color").asText(),"black");
        Assertions.assertEquals(car.get("type").asText(),"Mercedes");

    }

    @Test
    public void testObjectMapperWithTypeReference() throws JsonProcessingException {
        // List
        String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        List<Car> cars=new ObjectMapper().readValue(jsonCarArray,new TypeReference<List<Car>>(){});
        Assertions.assertEquals(cars.get(0).getType(),"BMW");
        Assertions.assertEquals(cars.get(0).getColor(),"Black");
        Assertions.assertEquals(cars.get(1).getType(), "FIAT");
        Assertions.assertEquals(cars.get(1).getColor(),"Red");
        // Map
        String jsonCar="{\"color\":\"black\",\"type\":\"Mercedes\"}";
        Map<String,String> carMap= new ObjectMapper().readValue(jsonCar, new TypeReference<Map<String, String>>() { });
        Assertions.assertEquals(carMap.get("color"),"black");
    }

    @Test
    public void testCarIgnoreUnkownByConfigurer() throws JsonProcessingException {
        String jsonCarByConfigurerObjectMapper="{\"color\":\"black\",\"type\":\"Mercedes\",\"gas\":false}";
        CarIgnoreUnkown carByConfigurer= new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                .readerFor(CarIgnoreUnkown.class)
                .readValue(jsonCarByConfigurerObjectMapper);

        Assertions.assertNotNull(carByConfigurer);
        Assertions.assertEquals(carByConfigurer.getColor(),"black");
        Assertions.assertEquals(carByConfigurer.getType(),"Mercedes");
        ///////////////////////////////////////////////////////////////////////
        String jsonCarByAnnotations="{\"color\":\"white\",\"type\":\"BMW\",\"gas\":true}";
        CarIgnoreUnkown carByAnnotations=new ObjectMapper().readerFor(CarIgnoreUnkown.class).readValue(jsonCarByAnnotations) ;
        Assertions.assertNotNull(carByAnnotations);
        Assertions.assertEquals(carByAnnotations.getType(),"BMW");
        Assertions.assertEquals(carByAnnotations.getColor(),"white");
    }

    @Test
    public void testCarExceptionWithUnkownProperties(){
        String jsonCar="{\"color\":\"white\",\"type\":\"BMW\",\"gas\":true,\"exception\":\"UKNOWNElement\"}";
        Assertions.assertThrows(UnrecognizedPropertyException.class,()-> new ObjectMapper().readerFor(Car.class).readValue(jsonCar));
    }

    @Test
    public void testSampleMapSerializer() throws JsonProcessingException {
        Map<String,String>map= new HashMap<>();
        map.put("key","value");
        String result=new ObjectMapper().writeValueAsString(map);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result,"{\"key\":\"value\"}");
    }

    // used to test Map<Object,String> like Map<MyPairs,String>
    @Test
    public void testMapWithObjectKeyAndStringValue() throws JsonProcessingException {
        Map<MyPairs,String>objectKeyMap=new HashMap<>();
        objectKeyMap.put(new MyPairs("Fathy","Ahmed"),"code author");
        String result=new ObjectMapper().writeValueAsString(objectKeyMap);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result,"{\"Fathy and Ahmed\":\"code author\"}");
    }
}
