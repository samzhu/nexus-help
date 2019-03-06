package com.sam.nexushelp.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items",
        "continuationToken"
})
@Data
public class SearchResponse {
    @JsonProperty("items")
    private List<ComponentItem> ComponentItems;
    @JsonProperty("continuationToken")
    private String continuationToken;
}
