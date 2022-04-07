package com.example.myapplication.dagger_hilt.main

import androidx.lifecycle.ViewModel
import com.example.myapplication.dagger_hilt.data.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
    뷰모델에 Repository 를 주입하는 방법
    1. ViewModelFactory 사용
    2. dagger-hilt 를 통한 의존성 주입
*/

@HiltViewModel
class DaggerViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {

    fun getRepositoryHash():String = repository.toString()
}