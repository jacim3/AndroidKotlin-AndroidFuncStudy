package com.example.myapplication.dagger_hilt.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dagger_hilt.data.MyRepository
import com.example.myapplication.dagger_hilt.di.ApplicationModule
import com.example.myapplication.dagger_hilt.second.SecondActivity
import com.example.myapplication.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    private val activityViewModel by activityViewModels<DaggerViewModel>()      // 하위 프래그먼트를 관리하는 액티비티의 라이프사이클로..
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonActivity.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        binding.buttonFragment.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment2_to_SecondFragment)
        }
        Log.e("FirstFragment", repository.hashCode().toString())
        Log.e("FirstFragment", applicationHash)
        Log.e("FirstFragment", application)
        Log.e("FirstFragment", viewModel.getRepositoryHash())
        Log.e("FirstFragment", activityViewModel.getRepositoryHash())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}