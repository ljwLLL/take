package com.hpower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableQualityControlCore
@EnableScheduling
public class QualityControlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QualityControlApiApplication.class, args);
    }
}
