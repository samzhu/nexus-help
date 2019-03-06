package com.sam.nexushelp.dto.task;

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
public class ListTasksResponse {
    @JsonProperty("items")
    public List<TaskItem> taskItems;
    @JsonProperty("continuationToken")
    public String continuationToken;
}
