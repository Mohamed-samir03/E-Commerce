package com.mosamir.e_commerce.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mosamir.e_commerce.R

const val channelId = "notification_channel"
const val channelName = "com.mosamir.e_commerce"

class MyFirebaseInstanceIDService : FirebaseMessagingService()  {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Handle the received message here
        val title = remoteMessage.notification?.title
        val message = remoteMessage.notification?.body

        // You can display a notification to the user
        sendNotification(title, message)

    }

    private fun sendNotification(title: String?, message: String?) {

        val intent = Intent(this,HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE
        )

        // Create a notification channel (for Android Oreo and later)
        var notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        notificationBuilder = notificationBuilder.setContent(
            getCustomDesign(title, message)
        )

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the notification channel (for Android Oreo and later)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Show the notification
        notificationManager.notify(0, notificationBuilder.build())
    }

    @SuppressLint("RemoteViewLayout")
    private fun getCustomDesign(title: String?, message: String?): RemoteViews? {

        val remoteViews = RemoteViews("com.mosamir.e_commerce.util",R.layout.notification)

        remoteViews.setTextViewText(R.id.tv_notification_title,title)
        remoteViews.setTextViewText(R.id.tv_notification_message,message)
        remoteViews.setImageViewResource(R.id.img_notification,R.drawable.logo)

        return remoteViews

    }


}