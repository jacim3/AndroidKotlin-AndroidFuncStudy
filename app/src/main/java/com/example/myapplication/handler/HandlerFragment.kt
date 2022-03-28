package com.example.myapplication.handler

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.HandlerFragmentBinding


/*
*
* 핸들러는 항상 자신을 생성하는 쓰레드에 부착되어, 해당 쓰레드의 메시지큐를 통해 다른 쓰레드와 통신을 진행
* 보통의 경우 다른 쓰레드에서 보낸 메시지를 수신하나, 자기 자신이 보낸 메시지를 수신할 수도 있음
* 메시지가 도착 시,
*
* */
class HandlerFragment : Fragment() {

    private lateinit var viewModel: HandlerViewModel
    private var binding: HandlerFragmentBinding? = null
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


        binding!!.buttonMusic.setOnClickListener {

            Thread(MusicThread(binding!!.progressBarMusic, requireActivity()), this)
        }

        binding!!.buttonMovie.setOnClickListener {
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    companion object {
        fun newInstance() = HandlerFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}