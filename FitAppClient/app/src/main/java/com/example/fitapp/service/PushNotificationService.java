package com.example.fitapp.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.fitapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FCMService", "Refreshed token: " + token);
        // Update token to server?
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FCMService", "From: " + message.getFrom());

        // Check if message contains a data payload.
        if (message.getData().size() > 0) {
            Log.d("FCMService", "Message data payload: " + message.getData());
        }

        // Check if message contains a notification payload.
        if (message.getNotification() != null) { // If notification recieved
            // Create notification message
            RemoteMessage.Notification notification = message.getNotification();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, String.valueOf(R.string.default_notification_channel_id))
                    //.setSmallIcon(R.drawable.not)
                    .setContentTitle(notification.getTitle())
                    .setContentText(notification.getBody())
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        // Respond to receive messages
    }
}
