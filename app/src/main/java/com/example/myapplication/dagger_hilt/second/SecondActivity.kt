package com.example.myapplication.dagger_hilt.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.dagger_hilt.data.MyRepository
import com.example.myapplication.dagger_hilt.di.ApplicationModule
import com.example.myapplication.dagger_hilt.main.DaggerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    // 다른 액티비티에서도 다른 뷰모델 접근이 가능하다!!! 와후~~~~~!!!
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.e("SecondActivity", repository.hashCode().toString())
        Log.e("SecondActivity", applicationHash)
        Log.e("SecondActivity", application)
        Log.e("SecondActivity", viewModel.getRepositoryHash())

    }
}