package com.example.myapplication.broadcast_receiver_service

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.BroadcastFragmentBinding
import com.example.myapplication.databinding.RecyclerviewFragmentBinding
import com.example.myapplication.recyclerview.RecyclerViewViewModel

class BroadCastServiceFragment : Fragment() {




    private lateinit var viewModel: BroadCastViewModel
    private lateinit var binding: BroadcastFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BroadCastViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BroadcastFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    companion object {
        fun newInstance() = BroadCastServiceFragment()
    }

}