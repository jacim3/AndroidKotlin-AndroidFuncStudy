package com.example.myapplication.main_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.myapplication.MainActivity

class MainViewModel : ViewModel() {

    fun moveToFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction().addToBackStack(null).replace(MainActivity.MAIN_Container, fragment).commit()
    }
}