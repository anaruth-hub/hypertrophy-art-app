package com.anaruth.hypertrophyartapp;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityRequirement(name = "bearerAuth")
public class HypertrophyArtAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypertrophyArtAppApplication.class, args);
    }

}
