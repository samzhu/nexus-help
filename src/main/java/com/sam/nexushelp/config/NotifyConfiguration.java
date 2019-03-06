package com.sam.nexushelp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "notify")
public class NotifyConfiguration {
    /**
     * notify info
     */
    private Mail mail;
}
