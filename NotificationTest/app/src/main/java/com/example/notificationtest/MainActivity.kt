package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.resources.Compatibility
import androidx.core.app.NotificationCompat
import java.nio.file.attribute.AclEntry.Builder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val sendNotice: Button = findViewById(R.id.sendNotice)
        sendNotice.setOnClickListener {
            val notification=NotificationCompat.Builder(this,"normal")//compat兼容
                .setContentTitle("就让这大雨落下")
                .setContentText("假装看不见我脸上的挣扎")
                .setSmallIcon(R.drawable.notice)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.big_notice))
                .build()
            manager.notify(1,notification)
        }
    }
}