package com.example.myapplication.handler

import android.app.Activity
import android.content.Context
import android.graphics.Movie
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HandlerViewModel : ViewModel() {

    // val currentNumber = MutableLiveData<Int>()  // ViewModel 에서 관리하는 라이브 데이터 홀더. 쓰레드 실행시 증가.
    var currentNumber = 0
    var musicStatus = ""
    var movieStatus = ""



    val musicGroup = ThreadGroup("musicGroup")
    val movieGroup = ThreadGroup("movieGroup")

    fun runThread1(textViewBackValue: TextView, activity: Activity) {

        // 4번 방식의 Handler 에서 받은 메시지를 별도 처리
        val mHandler: Handler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                //Log.e("HandlerMessage : ", "Passed")
                //textViewBackValue.text = msg.what.toString()

                when (msg.what) {
                    PLAY -> {
                    }
                    PAUSE -> {
                    }
                }
            }
        }

        // 쓰레드 부분
        Thread {
            while (true) {


                try {

                    // 뷰모델 데이터 홀더에 데이터 입력.
                    // 1.1 MutableLiveData 는 MainThread 에서만 setValue 가능. -> 대신 postValue() 사용
                    currentNumber++
                    Thread.sleep(250)
/* --------------------- WorkerThread 에서 MainThread 로 작업을 전달하는 방법 ------------------------ */

                    // 1. Activity.RunOnUiThread 이용
//                    activity.runOnUiThread(Runnable {
//                        textViewBackValue.text = currentNumber.value.toString()
//                    })

                    // 2. view.post 사용
//                    textViewBackValue.post {
//                        textViewBackValue.text = currentNumber.value.toString()
//                    }


                    // 3. Handler 사용
                    /*
                        Handler
                        핸들러는 객체를 만든 스레드 및 해당 스레드의 MessageQueue(FIFO) 에 바인딩 됨.
                        sendMessage() 는 message 를, post() 는 runnable 을 MessageQueue 에 전달

                        이렇게 MessageQueue 에 저장된 객체들은 차례대로 Looper에 의하여 Handler 로 전달되어,

                        * MessageQueue - Looper 가 전달할 메시지 목록을 보유하는 클래스
                        메시지는 직접 저장되는 것이 아닌, Looper 와 연결된 Handler 객체를 통하여 추가된다.
                    */
                    // 3.1 handler().post 를 이용
                    // 스코프 내부에서 곧바로 로직을 수행할 수 있으며, 또한 handleMessage()에 runnable 을 전달한다.
                    Handler(Looper.getMainLooper()).post {
                        textViewBackValue.text = currentNumber.toString()
                        Log.e("현재 스레드의 MessageQueue : ", Looper.myQueue().toString())
                    }

                    // 3.2 MessageQueue 애 메시지를 보내어 처리
                    /*
                    단순히 메시지 코드만 보내는 sendEmptyMessage() 에 비해 객체등 복잡한 Message 보내기 가능.
                    arg1:Int, arg2:Int, obj:Any, replyTo:Any,what:code
                    sendMessageAtTime(msg, long) : 메시지를 해당 시간 이후 전달.
                    sendMessageDelayed(msg, long) : 메시지를 현재 시간을 기준으로 딜레이 하여 전달
                    sendMessageAtFrontOfQueue(msg) : 메시지 큐 앞에 메시지 추가
                    */
                    val msg = Message().apply {
                        what = PLAY
                    }

                    // 3.
                    // 2.1 이렇게 보내진 메시지는 위 handler 의 handleMessage() 에서 처리된다.
                    mHandler.sendMessageAtFrontOfQueue(msg)

                } catch (e: InterruptedException) {
                    Log.e("쓰레드 종료", "쓰레드 종료")
                }
            }
        }.start()
    }

    companion object {
        const val PLAY = 0
        const val PAUSE = 1

        const val MUSIC = 0
        const val CHATTING = 1
        const val MOVIE = 2

        const val MUSIC_TEXT = "음악 : "
        const val CHATTING_TEXT = "채팅 : "
        const val MOVIE_TEXT = "영화 : "


    }
}