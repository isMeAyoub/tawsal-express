package com.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main Class of DemandesService
 * @Author: Ayoub ait si ahmad
 * @Date: 3/5/2024
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.simplon.clients"
)
public class DemandesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemandesServiceApplication.class,args);
    }
}
