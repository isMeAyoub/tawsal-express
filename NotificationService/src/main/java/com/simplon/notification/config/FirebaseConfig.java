package com.simplon.notification.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.simplon.NotificationServiceApplication;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class configures the Firebase Realtime Database.
 *
 * @Author Ayoub Ait Si Ahmad
 */
@Configuration
public class FirebaseConfig {
    // Load the Firebase configuration file
    ClassLoader classLoader = NotificationServiceApplication.class.getClassLoader();
    File file = new File(classLoader.getResource("firebase.json").getFile());
    FileInputStream serviceAccount;
    FirebaseOptions options = null;

    /**
     * This constructor initializes the Firebase Realtime Database.
     */
    public FirebaseConfig() {
        try {
            serviceAccount = new FileInputStream(file.getAbsolutePath());
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://tawsal-express-default-rtdb.firebaseio.com/")
                    .build();
            if (options != null) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}