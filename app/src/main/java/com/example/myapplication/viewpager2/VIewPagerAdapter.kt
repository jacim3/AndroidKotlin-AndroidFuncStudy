package com.example.myapplication.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.MainActivity

class VIewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return MainActivity.NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment = Tab1()

        when(position) {
            0 -> fragment = Tab1()
            1 -> fragment = Tab2()
            2 -> fragment = Tab3()
        }

        return fragment
    }

}