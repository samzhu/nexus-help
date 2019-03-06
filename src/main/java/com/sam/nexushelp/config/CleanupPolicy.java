package com.sam.nexushelp.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "policies-name",
        "field",
        "regex"
})
@Data
public class CleanupPolicy {
    @JsonProperty("policies-name")
    private String policiesName;
    @JsonProperty("field")
    private String field;
    @JsonProperty("regex")
    private String regex;
}
