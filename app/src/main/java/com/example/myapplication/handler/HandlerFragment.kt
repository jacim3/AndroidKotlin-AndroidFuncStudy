package com.example.myapplication.handler

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.HandlerFragmentBinding


/* *
*
* 핸들러는 항상 자신을 생성하는 쓰레드에 부착되어, 해당 쓰레드의 메시지큐를 통해 다른 쓰레드와 통신을 진행
* 보통의 경우 다른 쓰레드에서 보낸 메시지를 수신하나, 자기 자신이 보낸 메시지를 수신할 수도 있음
* 메시지가 도착 시,
*
* */
class HandlerFragment : Fragment() {

    private lateinit var viewModel: HandlerViewModel
    private var binding: HandlerFragmentBinding? = null

    lateinit var musicThread: Thread
    lateinit var chatThread: Thread
    lateinit var movieThread: Thread


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HandlerFragmentBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HandlerViewModel::class.java]

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // 1. 채팅쓰레드 활성
        //chatThread = Thread(MyThread(CHATTING, binding!!.textViewChatting, null, requireContext()))
        //chatThread.start()

        val builder = AlertDialog.Builder(requireContext())
        val dialog = builder.create()

        binding!!.buttonMusic.setOnClickListener {
            // 2. 뮤직쓰레드 활성
            Log.e("asdfasdf", threadStatus[MUSIC].toString())
            Log.e("asdfasdf", threadStatus[CHATTING].toString())
            Log.e("asdfasdf", threadStatus[MOVIE].toString())

            Thread(MusicThread(binding!!.progressBarMusic, requireContext())).start()
  /*          when (threadStatus[MUSIC]) {
                // 실행 준비중 - 언제든 조건만 된다면 실행
                Thread.State.NEW -> {
                    binding!!.buttonMusic.text = BUTTON_MUSIC_PAUSE
                    musicThread = Thread(
                        MyThread(
                            MUSIC,
                            binding!!.progressBarMusic,
                            binding!!.buttonMusic,
                            requireContext()
                        )
                    )
                    musicThread.start()
                }
                // 실행 중 or 실행 가능한 상태
                Thread.State.RUNNABLE -> {

                    // 쓰레드 실행 중 일시정지 하는 경우
                    if (binding!!.buttonMusic.text.equals(BUTTON_MUSIC_PAUSE)) {
                        binding!!.buttonMusic.text = BUTTON_MUSIC_PLAY
                        threadActions[MUSIC] = PAUSE        // 즉시 정지요청을 보낸다.
                    }
                    // Join() 을 통하여 쓰레드가 정지된 상태에서 다시 버튼을 누른 경우
                    else {
                        // TODO 비동기를 제대로 공부해야 함
                        Log.e("asdfasdf", "재개")
                        binding!!.buttonMusic.text = BUTTON_MUSIC_PAUSE
                        musicThread.interrupt()         // Join()으로 정지된 쓰레드를 다시 재개
                    }
                }
                // 스레드가 실행가능하지 않은 일시정지 상태 (TIMED_WAITING 는 지정된 시간동안 일시정지 상태)
                Thread.State.WAITING -> {

                }
                // 스레드가 종료된 상태
                Thread.State.TERMINATED -> {
                    musicThread = Thread(
                        MyThread(
                            MUSIC,
                            binding!!.progressBarMusic,
                            binding!!.buttonMusic,
                            requireContext()
                        )
                    )
                }
            }*/


        }

        binding!!.buttonMovie.setOnClickListener {
            // 3. 영화쓰레드 활성 -> 영화가 실행되면 음악은 정지되고, 채팅창은 우선도가 낮아지고, 정지.
/*            movieThread = Thread(
                MyThread(
                    MOVIE,
                    binding!!.progressBarMovie,
                    binding!!.buttonMovie,
                    requireContext()
                )
            )*/

        }
    }


    companion object {
        fun newInstance() = HandlerFragment()

        const val PLAY = 0
        const val PAUSE = 1
        const val WAITING = 2
        const val EXIT = 3

        const val MUSIC = 0
        const val CHATTING = 1
        const val MOVIE = 2

        const val BUTTON_MUSIC_PLAY = "음악 시작"
        const val BUTTON_MUSIC_PAUSE = "일시 정지"
        const val BUTTON_MOVIE_PLAY = "영화 정지"
        const val BUTTON_MOVIE_PAUSE = "일시 정지"

        val threadStatus = Array(3) { Thread.State.NEW }
        val threadActions = Array(3) { -1 }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}