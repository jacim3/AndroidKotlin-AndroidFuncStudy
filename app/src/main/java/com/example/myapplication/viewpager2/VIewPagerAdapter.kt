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

        return when(position) {
            0 -> Tab1()
            1 -> Tab2()
            2 -> Tab3()
            else -> Tab1()
        }
    }
}