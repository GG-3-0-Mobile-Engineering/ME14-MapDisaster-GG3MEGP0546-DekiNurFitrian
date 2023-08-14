package com.example.finalprojectgg.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.finalprojectgg.service.DisasterNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application(){
    override fun onCreate() {
        createNotificationChannel()
        super.onCreate()
    }
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DisasterNotificationService.TMA_CHANNEL_ID,
                "TMA",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Used for show updates related to Pintu Air"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}