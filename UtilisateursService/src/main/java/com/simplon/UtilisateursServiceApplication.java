package com.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main Class of UtilisateursService
 * @Author: Ayoub ait si ahmad
 * @Date: 3/5/2024
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.simplon.clients"
)
public class UtilisateursServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilisateursServiceApplication.class,args);
    }
}
