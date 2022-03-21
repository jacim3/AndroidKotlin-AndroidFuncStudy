package com.example.myapplication.service

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.ServiceFragmentBinding

class ServiceFragment : Fragment() {

    companion object {
        fun newInstance() = ServiceFragment()
    }

    private lateinit var viewModel: ServiceViewModel
    private lateinit var binding:ServiceFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServiceFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ServiceViewModel::class.java]
        binding.buttonPlay.setOnClickListener {
            viewModel.serviceStart(requireContext())
        }

        binding.buttonPause.setOnClickListener {
            viewModel.serviceStop(requireContext())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

}