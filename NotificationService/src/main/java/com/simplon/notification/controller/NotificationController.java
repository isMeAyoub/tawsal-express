package com.simplon.notification.controller;

import com.simplon.notification.model.entity.Notification;
import com.simplon.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This class represents the controller that handles the notification requests.
 *
 * @see com.simplon.notification.model.entity.Notification
 * @see com.simplon.notification.service.NotificationService
 * @Author Ayoub Ait Si Ahmad
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping("/send")
    public void sendNotification(
            @RequestBody @Valid Notification notification
    ) {
        log.info("Sending notification...");
        log.debug("Notification: {}", notification);
        notificationService.sendNotification(notification);
        log.info("Notification sent successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(
            @PathVariable String id
    ) {
        log.info("Deleting notification...");
        log.debug("Notification ID: {}", id);
        notificationService.deleteNotification(id);
        log.info("Notification deleted successfully");
    }
}
