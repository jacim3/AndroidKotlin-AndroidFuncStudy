package com.example.myapplication.viewpager2

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewpagerFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class ViewpagerFragment : Fragment() {

    companion object {
        fun newInstance() = ViewpagerFragment()
    }

    private lateinit var viewModel: ViewpagerFragmentViewModel
    private lateinit var binding: ViewpagerFragmentBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewpagerFragmentViewModel::class.java]
        // 액티비티 ViewModel 가져오기
        val sharedViewModel: com.example.myapplication.MainViewModel by activityViewModels()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewpagerFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 어댑터 및 뷰페이저2 설정
        val adapter = VIewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // TabLayout 과 뷰페이저2 연결
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager2,
        ) { tab: TabLayout.Tab?, position: Int -> }.attach()
        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_launcher_foreground)?.text = "1번"
        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_launcher_foreground)?.text = "2번"
        binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_launcher_foreground)?.text = "3번"

//        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_launcher_foreground)
//            ?.icon
//            ?.colorFilter = (ContextCompat.getColor(context, R.color.black), Por)

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }
}