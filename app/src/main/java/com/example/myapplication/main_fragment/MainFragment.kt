package com.example.myapplication.main_fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.broadcast_receiver.BroadCastFragment
import com.example.myapplication.coroutine.CoroutineFragment
import com.example.myapplication.dagger_hilt.main.DaggerActivity
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.handler.HandlerFragment
import com.example.myapplication.recyclerview.RecyclerViewFragment
import com.example.myapplication.retrofit.RetrofitFragment
import com.example.myapplication.service.ServiceFragment
import com.example.myapplication.viewpager2.ViewpagerFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val buttons = arrayOf(
            binding.appCompatButton1,
            binding.appCompatButton2,
            binding.appCompatButton3,
            binding.appCompatButton4,
            binding.appCompatButton5,
            binding.appCompatButton6,
            binding.appCompatButton7,
            binding.appCompatButton8,
            binding.appCompatButton9,
            binding.appCompatButton10,
            binding.appCompatButton11
        )

        val fragments = arrayOf(
            ViewpagerFragment(),
            RetrofitFragment(),
            RecyclerViewFragment(),
            BroadCastFragment(),
            ServiceFragment(),
            CoroutineFragment(),
            HandlerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
        )

        for (i: Int in buttons.indices) {
            buttons[i].setOnClickListener {
                activity?.let { it1 -> viewModel.moveToFragment(it1.supportFragmentManager,fragments[i]) }
            }
        }

        binding.daggerActivity.setOnClickListener {
            val intent = Intent(requireContext(), DaggerActivity::class.java)
            startActivity(intent)
        }
    }
}