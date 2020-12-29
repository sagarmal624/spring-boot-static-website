package com.pe.builddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class BuilddemoApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BuilddemoApplication.class);
    }

    @GetMapping("api/v1/msg")
    public String get() {
        return "Hello calling from Controller";
    }

    public static void main(String[] args) {
        SpringApplication.run(BuilddemoApplication.class, args);
    }

}
