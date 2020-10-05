package com.jackson.bealdung.material.dto.deserialize;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"propertyToIgnore1","propertyToIgnore2"})
//@JsonIgnoreType       // used to ignore all User Type
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class User {

    private int id;

    @JsonAlias({"fname","firstName","f_name"})
    private String firstName;
    @JsonAlias({"lname","lastName","l_name"})
    private String lastName;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date birthDate;

    private String propertyToIgnore1;
    private String propertyToIgnore2;
}
