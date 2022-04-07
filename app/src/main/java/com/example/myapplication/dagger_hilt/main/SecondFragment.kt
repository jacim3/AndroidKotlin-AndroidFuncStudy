package com.example.myapplication.dagger_hilt.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dagger_hilt.data.MyRepository
import com.example.myapplication.dagger_hilt.di.ApplicationModule
import com.example.myapplication.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val viewModel by viewModels<DaggerViewModel>()
    @Inject
    lateinit var repository: MyRepository

    @ApplicationModule.AppHash
    @Inject
    lateinit var applicationHash:String
    // Inject 는 타입을 추론하여 동작한다.

    @ApplicationModule.BppHash
    @Inject
    lateinit var application:String

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_firstFragment2)
        }

        Log.e("SecondFragment", repository.hashCode().toString())
        Log.e("SecondFragment", applicationHash)
        Log.e("SecondFragment", application)
        Log.e("SecondFragment", viewModel.getRepositoryHash())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}