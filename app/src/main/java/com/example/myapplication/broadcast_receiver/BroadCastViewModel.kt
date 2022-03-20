package com.example.myapplication.broadcast_receiver

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.util.*

class BroadCastViewModel : ViewModel() {
    private var requestCode = 0
    val alarmList =  ArrayList<String>()

    @RequiresApi(Build.VERSION_CODES.M)

    // 알람 설정 및 등록
    fun startAlarm(context: Context, alarmManager: AlarmManager, data:Long) {

        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        alarmIntent.putExtra("requestCode", requestCode)
        val sender = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, requestCode, alarmIntent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(context, requestCode, alarmIntent, 0)
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = data
        alarmList.add("${calendar.time}")
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, data,sender)

        requestCode++
    }
}