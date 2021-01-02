package com.jackson.bealdung.material.dto.jsonviews.customjsonviews;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;

public class MyBeanSerializerModifier extends BeanSerializerModifier {
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {

        for (int i=0;i<beanProperties.size();i++){
            BeanPropertyWriter propertyWriter=beanProperties.get(i);
            if(propertyWriter.getName().equalsIgnoreCase("name")) {
                beanProperties.set(i,new UpperCasingWriter(propertyWriter));
            }
        }
        return beanProperties;
    }
}
