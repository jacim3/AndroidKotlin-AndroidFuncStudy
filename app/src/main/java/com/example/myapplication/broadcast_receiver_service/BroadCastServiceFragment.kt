package com.example.myapplication.broadcast_receiver_service

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class BroadCastServiceFragment : Fragment() {

    companion object {
        fun newInstance() = BroadCastServiceFragment()
    }

    private lateinit var viewModel: BroadCastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.broad_cast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BroadCastViewModel::class.java)
        // TODO: Use the ViewModel
    }

}