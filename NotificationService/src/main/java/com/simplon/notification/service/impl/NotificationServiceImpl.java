package com.simplon.notification.service.impl;

import com.google.firebase.database.*;
import com.simplon.notification.model.entity.Notification;
import com.simplon.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;

/**
 * This class implements the NotificationService interface.
 * It provides methods to send and delete notifications using Firebase Realtime Database.
 *
 * @Author Ayoub Ait Si Ahmad
 * @see com.simplon.notification.service.NotificationService
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {


    /**
     * This method sends a notification to the clients using Firebase Realtime Database.
     *
     * @param notification The notification to send
     */
    @Override
    public void sendNotification(Notification notification) {
        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationsRef = database.getReference("notifications");

        // Generate a unique key for the notification
        String key = notificationsRef.push().getKey();
        log.debug("Notification key: {}", key);

        // Prepare the notification data
        Map<String, Object> notificationData = new HashMap<>();
        notificationData.put("title", notification.getTitle());
        notificationData.put("message", notification.getMessage());
        notificationData.put("triggerBy", notification.getTriggerBy());
        notificationData.put("triggerAt", LocalDate.now().toString());
        notificationData.put("eventType", notification.getEventType().name());
        log.debug("Notification data: {}", notificationData);

        // Save the notification to Firebase Realtime Database
        notificationsRef.child(key).setValueAsync(notificationData);
        log.info("Notification sent successfully");
    }

    /**
     * This method deletes a notification from Firebase Realtime Database.
     *
     * @param id The ID of the notification to delete
     */
    @Override
    public void deleteNotification(String id) {
        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationsRef = database.getReference("notifications");
        log.debug("Notification ID: {}", id);

        // Delete the notification from Firebase Realtime Database
        notificationsRef.child(id).removeValueAsync();
        log.info("Notification deleted successfully");
    }

    /**
     * The method of getting all notifications from Firebase Realtime Database.
     * Should be implemented in the frontend.
     *
     * @return A list of notifications
     */
}