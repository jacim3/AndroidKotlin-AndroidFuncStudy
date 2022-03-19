package com.example.myapplication.viewpager2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R

class ViewpagerFragment : Fragment() {

    companion object {
        fun newInstance() = ViewpagerFragment()
    }

    private lateinit var viewModel: ViewpagerFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.viewpager_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewpagerFragmentViewModel::class.java]

        // MainActivity 의 뷰모델 객체 접근
        val sharedViewModel:com.example.myapplication.MainViewModel by activityViewModels()
        val data = sharedViewModel.myResponse.value?.body()

        if (data != null) {
            Log.e("^&*^&*", data.id)
            Log.e("^&*^&*", data.userId.toString())
            Log.e("^&*^&*", data.body)
            Log.e("^&*^&*", data.title)
        }
        // TODO: Use the ViewModel
    }
}