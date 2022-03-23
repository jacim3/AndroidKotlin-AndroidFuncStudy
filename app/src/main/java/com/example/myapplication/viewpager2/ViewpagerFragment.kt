package com.example.myapplication.viewpager2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewpagerFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import me.relex.circleindicator.CircleIndicator3
import java.util.*


class ViewpagerFragment : Fragment() {

    private lateinit var viewModel: ViewpagerFragmentViewModel
    private var binding: ViewpagerFragmentBinding? = null
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
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 어댑터 및 뷰페이저2 설정
        val pagerAdapter = VIewPagerAdapter(childFragmentManager, lifecycle)

        // ViewPager 의 Position 변경에 따라 이를 가리킬 Indicator 설정
        this.binding!!.circleIndicator.run {
            setViewPager(binding!!.viewPager2)
            createIndicators(MainActivity.NUM_TABS, 0)
        }

        // ViewPager2 설정
        binding!!.viewPager2.apply {
            adapter = pagerAdapter      // 어댑터 연결
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding!!.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // Circle Indicator 가 ViewPager 의 Position 에 따라 변경되도록 설정.
                    binding!!.circleIndicator.animatePageSelected(position%MainActivity.NUM_TABS)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
        }

        // TabLayout 과 뷰페이저2 연결
        TabLayoutMediator(
            binding!!.tabLayout, binding!!.viewPager2,
        ) { tab: TabLayout.Tab?, position: Int -> }.attach()

        // 각 Position 별로 생성된 탭에 대하여 텍스트 혹은 아이콘을 별도로 설정할 수 있다.
        for (position:Int in 0 until MainActivity.NUM_TABS) {
            binding!!.tabLayout.getTabAt(position)?.setIcon(R.drawable.ic_launcher_foreground)?.text = "탭 $position"
        }

//        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_launcher_foreground)
//            ?.icon
//            ?.colorFilter = (ContextCompat.getColor(context, R.color.black), Por)

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