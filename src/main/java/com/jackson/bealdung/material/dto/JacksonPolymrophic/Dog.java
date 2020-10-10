package com.jackson.bealdung.material.dto.JacksonPolymrophic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("dog")
public class Dog extends Animal{
    @JsonProperty("sound")
    private double barkVolume;
}
