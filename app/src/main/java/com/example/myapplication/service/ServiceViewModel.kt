package com.example.myapplication.service

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class ServiceViewModel : ViewModel() {

    fun serviceStart(context: Context) {
        // 보안을 위해서, 서비스는 반드시 명시적 인텐트를 사용해야 한다.
        val intent = Intent(context,MyService::class.java)
        // 포그라운드 서비스 버전 처리
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            context.startForegroundService(intent)
         else
            context.startService(intent)
    }

    fun serviceStop(context: Context) {
        val intent = Intent(context, MyService::class.java)
        context.stopService(intent)
    }
}