package com.example.myapplication.reactive

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentReactiveBinding
import com.example.myapplication.viewpager2.ViewpagerFragment
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class ReactiveFragment : Fragment() {

    // private lateinit var viewModel: ViewpagerFragmentViewModel
    private var binding: FragmentReactiveBinding? = null
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
        binding = FragmentReactiveBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GithubClient.getApi().getRepos("jacim3").subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({items ->
                items.forEach { println(it) }
            }, {e ->
                println(e.printStackTrace())
            })

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = ViewpagerFragment()
    }
}