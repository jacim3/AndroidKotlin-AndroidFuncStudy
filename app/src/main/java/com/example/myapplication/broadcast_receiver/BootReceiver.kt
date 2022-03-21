package com.example.myapplication.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootReceiver : BroadcastReceiver() {

    // 디바이스 부팅 후 이전에 보내놓았던 리시버를 통하여 수행
    override fun onReceive(p0: Context?, p1: Intent?) {
        // TODO 부팅후 수행할 로직
        Toast.makeText(p0, "BootReceiver Activated", Toast.LENGTH_SHORT).show()
    }
}