package com.simplon.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main Class of MediaSafeService
 *
 * @EnableDiscoveryClient: This annotation is used to make the service register itself with the discovery server.
 * @Author: Ayoub ait si ahmad
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MediaSafeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaSafeServiceApplication.class, args);
    }
}
