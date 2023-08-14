package com.example.finalprojectgg.service

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.finalprojectgg.R

class DisasterNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(
        title:String,
        text:String
    ){
        val notification = NotificationCompat.Builder(context, TMA_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_flood)
            .setContentTitle(title)
            .setContentText(text)
            .build()

        notificationManager.notify(UPDATE_TMA_ID, notification)
    }
    companion object{
        const val TMA_CHANNEL_ID = "tma_channel"
        const val UPDATE_TMA_ID = 1
    }
}