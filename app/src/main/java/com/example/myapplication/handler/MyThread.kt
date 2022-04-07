package com.example.myapplication.handler

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.utils.widget.MockView
import com.example.myapplication.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.lang.Runnable
import java.lang.StringBuilder
import java.util.*
import kotlin.coroutines.coroutineContext


/*
    Thread 의 인스턴스를 생성하는 방법은
    1. Thread 를 상속받은 클래스 생성
    2. Runnable 을 구현한 클래스 생성 -> 패러미터 및 다른 클래스의 상속이 가능하므로 보다 객체지향적인 방법
    단, Runnable 을 구현한 경우, 해당 클래스의 인스턴스를 Thread(runnable) 의 매개변수로 제공해야 쓰레드 실행이 가능

    일반적으로 두 개의 작업을 처리하는 경우,
    단일 쓰레드인 경우, 하나의 작업이 끝난 후 다음 작업을 수행하나 두 개의 쓰레드로 처리하는 경우 짧은 시간동안 두 개의
    작업이 번갈아가며 수행되므로, 동시에 작업이 처리되는것 처럼 보이게 만든다.
    * 프로세스 혹은 쓰레드간 작업 전환을 ContextSwitching 이라 하고, 이를 위한 정보를 저장하는데에 리소스가 사용되며,
    한 스레드가 작업중일 때 다른 쓰레드는 대기해야 하므로, 단순한 작업의 경우, 단일쓰레드 작업이 다중쓰레드 작업보다 효율적이다.
    단, 각각의 스레드가 서로 다른 자원을 사용하는 경우는, 멀티 프로세스가 확실히 유리하다.

    쓰레드는 OS 독립적인 Java 와는 달리 OS 종속적인 특성을 가진다.
    쓰레드는 JVM 쓰레드 스케쥴러에, 프로세스는 OS 프로세스 스케쥴러에 의하여 영향을 받으므로, 매 순간 쓰레드와 프로세스에
    할당되는 시간이 불확실하다는 특성이 있다.

    쓰레드는 Priority 라는 속성을 가지고 있어, 이 값에 따라 쓰레드가 얻는 실행시간이 달라진다.
    UI 작업을 하는 쓰레드의 우선 순위는 다른 작업을 수행하는 쓰레드보다 반드시 높아야 한다.

    쓰레드 그룹 : 서로 관련된 쓰레드를 그룹으로 다루기 위한 것. 쓰레드 그룹에 하위 쓰레드 그룹을 포함시킬 수 있으며,
    다른 쓰레드그룹은 변경할 수 없다.

    DaemonThread : DaemonThread 가 아닌, 다른 일반 쓰레드의 작업을 독는 보조쓰레드로 일반 쓰레드가 모두 종료되면
    종료.

]*/



abstract class MyThread {

    val PLAY = 0
    val PAUSE = 1
    val WAITING = 2
    val EXIT = 3

    val MUSIC = 0
    val CHATTING = 1
    val MOVIE = 2

    val BUTTON_MUSIC_PLAY = "음악 시작"
    val BUTTON_MUSIC_PAUSE = "일시 정지"
    val BUTTON_MOVIE_PLAY = "영화 정지"
    val BUTTON_MOVIE_PAUSE = "일시 정지"

    val DELAY_MILLS = arrayOf(250L, 1500L, 1000L)
    val TITLE = arrayOf("MUSIC", "CHATTING", "MOVIE")

    abstract fun doThread()
}

class MusicThread(var musicBar: ProgressBar, val context: Context) : Runnable, MyThread() {
    override fun run() {
        while (true) {
            doThread()
        }
    }

    override fun doThread() {

  /*      try {
            // 맨 처음 루프 시작 시 상태값 저장.
            HandlerFragment.threadStatus[code] = Thread.currentThread().state

            // UI 를 수정하기 위해 MainThread 에 접근해야 하므로, 이러한 변경 요청을
            // Handler 의 MessageQueue 에 전달하기 위함.

            when (HandlerFragment.threadActions[code]) {
                MyThread.PAUSE -> {

                    val deff = CoroutineScope(IO).async {
                        HandlerFragment.threadActions[code] = MyThread.WAITING
                    }

                    CoroutineScope(IO).launch {
                        deff.await()
                    }
                }
            }

            val msg = Message().apply {
                what = code
            }
            WidgetHandler(widget, context, Thread.currentThread())
                .sendMessage(msg)

            // 쓰레드 딜레이
            Thread.sleep(DELAY_MILLS[code])

        } catch (e: InterruptedException) {
            // 종료신호를 받았으므로, 쓰레드 루프 종료.
            if (HandlerFragment.threadActions[code] == EXIT) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "${TITLE[code]} 종료 ", Toast.LENGTH_SHORT).show()

                    if (code == MUSIC) {
                        button!!.text = BUTTON_MUSIC_PLAY
                    } else if (code == MOVIE) {
                        button!!.text = BUTTON_MOVIE_PLAY
                    }
                }
            }
        }*/
    }
}

class ChatThread(val chatBox: TextView, val context: Context) : Runnable {
    override fun run() {
    }

}

class MovieThread(val movieBar: ProgressBar, val context: Context) : Runnable {
    override fun run() {

    }
}

class WidgetHandler(var widget: View, var context: Context, var currentThread: Thread) :
    Handler(Looper.getMainLooper()) {
    // MainThread 에서 실행되므로, 절대 Thread.currentThread() 로 정지하면 안 된다.
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)

        val chatNames = arrayOf("철수", "영희", "민수", "지수", "광철")

        when (msg.what) {
            // 뮤직쓰레드 UI 요청 메시지
            HandlerFragment.MUSIC -> {

                val musicBar: ProgressBar = widget as ProgressBar
                musicBar.incrementProgressBy(1)
                if (musicBar.progress >= 20) {
                    musicBar.progress = 0
                    //HandlerFragment.threadActions[MUSIC] = EXIT
                    currentThread.interrupt()
                }
            }

            // 채팅쓰레드 UI 요청 메시지
            HandlerFragment.CHATTING -> {

                val chatBox: TextView = widget as TextView
                val currentMessage =
                    "${chatNames[Random().nextInt(chatNames.size)]} : ${getRandomString()} \n"
                chatBox.text = "${chatBox.text}\n $currentMessage"
                if (chatBox.lineCount > 24) {
                    chatBox.text = ""
                }
            }

            // 무비쓰레드 UI 요청 메시지
            HandlerFragment.MOVIE -> {
                val movieBar: ProgressBar = widget as ProgressBar
                movieBar.incrementProgressBy(1)
                if (movieBar.progress >= 30) {
                    movieBar.progress = 0
                }
            }
        }
    }

    private fun getRandomString(): StringBuffer {

        val rnd = Random();
        val buf = StringBuffer();
        for (i: Int in 0..20) {

            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
            if (rnd.nextBoolean()) {
                buf.append((rnd.nextInt(26) + 97).toChar());
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }
        return buf
    }
}








