package com.kowihan.acceleo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootAcceleoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAcceleoApplication.class, args);
    }
}

