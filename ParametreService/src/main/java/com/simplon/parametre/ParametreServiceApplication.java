package com.simplon.parametre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main Class of ParametreService
 * @Author: Ayoub ait si ahmad
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ParametreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParametreServiceApplication.class,args);
    }
}
