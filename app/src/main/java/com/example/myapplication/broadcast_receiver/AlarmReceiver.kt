package com.example.myapplication.broadcast_receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class AlarmReceiver : BroadcastReceiver() {

    // 목표시간이 되었을 때, AlarmManager 를 통해 지정한 시간에 따라 로직 수행.
    override fun onReceive(context: Context, intent: Intent) {

        // TODO alarmManager 를 통하여 지정한 시간이 되었을 때, 여기로 PendingIntent 에 감싸서 보내졌던 Intent
        // TODO 데이터와 Context 정보를 함께 보내준다.
        // 현재 알람은 설정한 시간과 목표 시간만 체크.

        val requestCode = intent.getIntExtra("requestCode", -1)
        Log.e("AlarmReceiver : ", requestCode.toString())
        if (requestCode == -1) return

        val notifyIntent =
            Intent(Intent.ACTION_VIEW).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val notifyManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, "default")

        // 오레오부터 알람채널 필요
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
            val importance = NotificationManager.IMPORTANCE_HIGH // 소리 + 메시지

            val channel = NotificationChannel("default", "채널 이름", importance)
            channel.description = "알람입니다."

            notifyManager.createNotificationChannel(channel)
        } else
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)

        builder.setAutoCancel(false)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis()).setShowWhen(true)

        notifyManager.notify(0, builder.build())
    }
}