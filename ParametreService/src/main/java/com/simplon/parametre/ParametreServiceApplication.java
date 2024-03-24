package com.simplon.parametre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main Class of ParametreService
 *
 * @EnableDiscoveryClient: This annotation is used to make the application register itself with the discovery server.
 * @EnableFeignClients: This annotation is used to enable the Feign client in the application.
 * @Author: Ayoub ait si ahmad
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.simplon.clients"
)
public class ParametreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParametreServiceApplication.class, args);
    }
}
