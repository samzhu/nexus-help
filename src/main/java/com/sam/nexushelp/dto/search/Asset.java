package com.sam.nexushelp.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "downloadUrl",
        "path",
        "id",
        "repository",
        "format",
        "checksum"
})
@Data
public class Asset {
    @JsonProperty("downloadUrl")
    private String downloadUrl;
    @JsonProperty("path")
    private String path;
    @JsonProperty("id")
    private String id;
    @JsonProperty("repository")
    private String repository;
    @JsonProperty("format")
    private String format;
    @JsonProperty("checksum")
    private Checksum checksum;
}