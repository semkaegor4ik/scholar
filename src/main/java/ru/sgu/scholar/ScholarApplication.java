package ru.sgu.scholar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ru.sgu.scholar")
public class ScholarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScholarApplication.class, args);
    }

}
