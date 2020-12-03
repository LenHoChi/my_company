package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@SpringBootApplication
//@EnableJpaAuditing
public class CompanyApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Xin cao moi nguoi na!");



    }
}
