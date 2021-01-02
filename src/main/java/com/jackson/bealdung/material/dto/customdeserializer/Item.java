package com.jackson.bealdung.material.dto.customdeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = ItemCustomDeserializer.class)
public class Item {
    private int id;
    private String itemName;
    private User owner;
}
