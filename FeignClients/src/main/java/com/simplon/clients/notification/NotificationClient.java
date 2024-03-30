package com.simplon.clients.notification;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "NOTIFICATION")
public interface NotificationClient {

    @PostMapping("/api/v1/notification/send")
    void sendNotification(
            @RequestBody @Valid Notification notification
    );

    @DeleteMapping("/api/v1/notification/{id}")
    void deleteNotification(
            @PathVariable("id") String id
    );
}
