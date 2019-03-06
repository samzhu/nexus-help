package com.sam.nexushelp.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "repository",
        "format",
        "group",
        "name",
        "version",
        "assets"
})
@Data
public class ComponentItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("repository")
    private String repository;
    @JsonProperty("format")
    private String format;
    @JsonProperty("group")
    private Object group;
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;
    @JsonProperty("assets")
    private List<Asset> assets;
}