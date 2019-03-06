package com.sam.nexushelp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mail {
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("subject")
    private String subject;
}
