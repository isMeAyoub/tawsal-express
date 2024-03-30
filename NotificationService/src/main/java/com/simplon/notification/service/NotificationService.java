package com.simplon.notification.service;

import com.simplon.notification.model.entity.Notification;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This interface provides methods to send and delete notifications.
 *
 * @Author Ayoub Ait Si Ahmad
 */
public interface NotificationService {
    void sendNotification(Notification notification);
    void deleteNotification(String id);
}