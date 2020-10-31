package com.jackson.bealdung.material.dto.serialize;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MyPairs {
    private String first;
    private String last;

    @JsonValue
    @Override
    public String toString() {
        return this.first+" and "+this.last;
    }
}
