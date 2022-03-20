package com.example.myapplication.broadcast_receiver

import android.app.AlarmManager
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.myapplication.databinding.BroadcastFragmentBinding
import java.util.*
import kotlin.collections.HashMap

class BroadCastServiceFragment : Fragment() {

    private lateinit var viewModel: BroadCastViewModel
    private lateinit var binding: BroadcastFragmentBinding
    private var requestCode = 0
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        binding.buttonAlarm.setOnClickListener {
            val dataSet = HashMap<String, Int>()
            val select = Calendar.getInstance()

            Log.e("!@#!@#!@#", binding.timePicker.hour.toString())
            select.set(Calendar.HOUR, binding.timePicker.hour);
            select.set(Calendar.MINUTE, binding.timePicker.minute);
            select.set(Calendar.SECOND, 0);
            
            viewModel.startAlarm(requireContext(), alarmManager, select.timeInMillis)
            requestCode++
        }

    }

    companion object {
        fun newInstance() = BroadCastServiceFragment()
    }

}