package com.example.springblog2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Springblog2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springblog2Application.class, args);
    }

}
