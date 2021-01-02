package com.jackson.bealdung.material.dto.jsonviews;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @JsonView(Views.Public.class)
    private int id;
    @JsonView(Views.Public.class)
    private String orderName;
    @JsonView(Views.Internal.class)
    private String ownerName;
}
