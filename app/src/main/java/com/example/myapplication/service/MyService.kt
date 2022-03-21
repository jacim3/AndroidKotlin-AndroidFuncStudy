package com.example.myapplication.service

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class MyService : Service() {

/*
    서비스
    백그라운드에서 오래 실행되는 작업을 수행할 수 있는 Component 이며, UI를 제공하지 않는다.
    에플리케이션 구성요소가 서비스를 시작하며, 사용자가 다른 애플리케이션으로 전환하더라도 백그라운드에서
    계속 실행된다. 또한 컴포넌트를 서비스에 바인딩하여, 서비스와 상호작용 가능하며, 프로세스간 통신도 가능하다.

    * ForegroundService : Notification(음악, 다운로드 등) 을 표시하는 등 사용자에게 잘 보이는 작업 수행
    -> 알림만 살아있으면, 서비스 유지
    * BackgroundService : 사용자에게 보이지 않는 작업을 수행. -> 어플리케이션 완전 종료 시, 서비스 소멸
    * BindService : 컴포넌트가 bindService()를 호출하여, 서비스 바인딩 수행. 컴포넌트와 서비스간 상호작용이 가능하고, 프로세스간 통신도 가능.
    다른 구성요소 또한 서비스에 바인딩하며, 이렇게 바인딩 된 서비스들을 통하여 클라이언트-서버 인터페이스를 구현한다.
    모든 바인딩된 컴포넌트가 해제되면 해당 서비스는 소멸한다.
*/

    lateinit var mediaPlayer: MediaPlayer

    // 바인드 허용 콜백
    // 컴포넌트가 서비스에 바인딩하고자 할때 수행
    // 클라이언트가 서비스와 통신을 수고받기 위해 사용할 인터페이스를 여기서 제공해야 한다.
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // 서비스가 처음 생성되었을때, 수행.
    // 서비스에 대한 일회성 설정 로직은 여기서 수행
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.chacha)
        mediaPlayer.isLooping = false // 반복 재생
    }

    // 서비스 시작 콜백
    // 컴포넌트가 서비스 사용을 요청할때마다 수행된다.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()

        val notificationIntent = Intent(this, ServiceFragment::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, notificationIntent, 0)
        }

        val notification = NotificationCompat.Builder(this, "default").apply {
            setContentTitle("Music Player")
            setContentText("음악이 재생중입니다.")
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentIntent(pendingIntent)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager:NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            /*
            1. IMPORTANCE_HIGH = 알림음이 울리고 헤드업 알림으로 표시
            2. IMPORTANCE_DEFAULT = 알림음 울림
            3. IMPORTANCE_LOW = 알림음 없음
            4. IMPORTANCE_MIN = 알림음 없고 상태줄 표시 X
            */
            manager.createNotificationChannel(NotificationChannel("default","기본 채널",NotificationManager.IMPORTANCE_LOW))
        }
        startForeground(2, notification.build())
        /*
        1. START_STICKY = Service 가 재시작될 때 null intent 전달
        2. START_NOT_STICKY = Service 가 재시작되지 않음
        3. START_REDELIVER_INTENT = Service 가 재시작될 때 이전에 전달했던 intent 전달
        */
        return START_STICKY
    }

    // 서비스가 종료 콜백백
    // 서비스가 더이상 사용되지 않고 소멸될때 수행
   override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}