package com.jackson.bealdung.material.dto.jsonviews.customjsonviews;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.jackson.bealdung.material.dto.jsonviews.User;

import java.util.Objects;

public class UpperCasingWriter extends BeanPropertyWriter {
    BeanPropertyWriter _writer;

    public UpperCasingWriter( BeanPropertyWriter _writer) {
        super(_writer);
        this._writer = _writer;
    }

    @Override
    public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        String value= ((User)bean).getName();
        value= (Objects.nonNull(value))? value.toUpperCase(): "";
        gen.writeStringField("name",value);
    }
}
