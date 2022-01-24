package com.example.nailshopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NailshopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NailshopBackendApplication.class, args);
    }

}
