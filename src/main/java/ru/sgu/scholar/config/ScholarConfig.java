package ru.sgu.scholar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.scholar")
@Data
public class ScholarConfig {
    private String api;
    private String apiKey;
}
