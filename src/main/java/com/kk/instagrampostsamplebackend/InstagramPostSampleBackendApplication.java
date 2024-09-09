package com.kk.instagrampostsamplebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InstagramPostSampleBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramPostSampleBackendApplication.class, args);
    }

}
