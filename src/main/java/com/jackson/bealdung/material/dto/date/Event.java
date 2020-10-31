package com.jackson.bealdung.material.dto.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("eventDate")
    private Date eventDate;
}
