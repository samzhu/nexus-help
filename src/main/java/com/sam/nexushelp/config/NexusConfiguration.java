package com.sam.nexushelp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "nexus")
public class NexusConfiguration {
    /**
     * nexus url
     */
    @JsonProperty("url")
    private String url;
    /**
     * login username
     */
    @JsonProperty("username")
    private String username;
    /**
     * login password
     */
    @JsonProperty("password")
    private String password;
    /**
     * clean repository
     */
    @JsonProperty("repository")
    private String repository;
    /**
     * clean format
     */
    @JsonProperty("format")
    private String format;
    /**
     * policies list
     */
    @JsonProperty("cleanup-policies")
    private List<CleanupPolicy> cleanupPolicies;
}
