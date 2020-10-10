package com.jackson.bealdung.material.dto.JacksonPolymrophic;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("cat")
public class Cat extends Animal{
    boolean likesCream;
    int lives;

}
