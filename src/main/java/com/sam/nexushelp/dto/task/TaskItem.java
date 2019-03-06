package com.sam.nexushelp.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "type",
        "message",
        "currentState",
        "lastRunResult",
        "nextRun",
        "lastRun"
})
@Data
public class TaskItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("message")
    private String message;
    @JsonProperty("currentState")
    private String currentState; //  WAITING
    @JsonProperty("lastRunResult")
    private String lastRunResult;
    @JsonProperty("nextRun")
    private String nextRun;
    @JsonProperty("lastRun")
    private String lastRun;
}
