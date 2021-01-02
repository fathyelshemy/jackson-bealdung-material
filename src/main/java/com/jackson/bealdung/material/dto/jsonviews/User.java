package com.jackson.bealdung.material.dto.jsonviews;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    @JsonView({Views.Public.class})
    private String name;
}
