package com.example.myapplication.broadcast_receiver

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.util.*

class BroadCastViewModel : ViewModel() {
    private var requestCode = 0
    val alarmList = ArrayList<String>()


    // 알람 설정 및 등록
    fun startAlarm(context: Context, activity: Activity, data: Long) {
        // TODO 현재는 설정한 시간을 밀리초로 환산하여 해당 시간에 BroadCastReceiver 를 작동시켜 로직을 수행.

        val alarmManager by lazy { activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager }
        // 리시버 지정
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        alarmIntent.putExtra("requestCode", requestCode)
        val sender = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(
                context,
                requestCode,
                alarmIntent,
                PendingIntent.FLAG_MUTABLE
            )
        } else {
            PendingIntent.getBroadcast(context, requestCode, alarmIntent, 0)
        }

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                Log.e("here", "hre : $requestCode")
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, data, sender)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ->
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, data, sender)
            else -> alarmManager.set(AlarmManager.RTC_WAKEUP, data, sender)
        }
        Log.e("BroadCastViewModel", "managerSend : $requestCode")
        requestCode++
    }

    fun cancelAlarm(context: Context, alarmManager: AlarmManager) {
        // TODO 알람 취소하기
        val requestCode = 0
        val alarmIntent = Intent(context, AlarmReceiver::class.java)

        val sender by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    alarmIntent,
                    PendingIntent.FLAG_MUTABLE
                )
            } else {
                PendingIntent.getBroadcast(context, requestCode, alarmIntent, 0)
            }
        }
        alarmManager.cancel(sender)         // alarmManager 취소
    }
}