package com.sam.nexushelp.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sha1",
        "sha256"
})
@Data
public class Checksum {
    @JsonProperty("sha1")
    private String sha1;
    @JsonProperty("sha256")
    private String sha256;
}
