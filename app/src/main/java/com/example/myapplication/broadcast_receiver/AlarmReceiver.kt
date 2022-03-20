package com.example.myapplication.broadcast_receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val requestCode = intent.getIntExtra("requestCode", -1)

        if (requestCode == -1) return

        val notifyIntent =
            Intent(Intent.ACTION_VIEW).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val notifyManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, "default")
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context,
                requestCode,
                notifyIntent,
                PendingIntent.FLAG_MUTABLE
            )
        } else {
            PendingIntent.getActivity(context, requestCode, notifyIntent, 0)
        }

        // 오레오부터 알람채널 필요
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
            val name = "채널 이름"
            val desc = "알람입니다."
            val importance = NotificationManager.IMPORTANCE_HIGH // 소리 + 메시지

            val channel = NotificationChannel("default", name, importance)
            channel.description = desc

            notifyManager.createNotificationChannel(channel)
        } else
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)

        builder.setAutoCancel(false)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())

        notifyManager.notify(requestCode, builder.build())
//        val notificationIntent = Intent(Intent.ACTION_VIEW, Uri.parse)
    }
}