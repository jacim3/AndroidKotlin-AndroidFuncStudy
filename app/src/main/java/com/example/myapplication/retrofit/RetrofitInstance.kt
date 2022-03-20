package com.example.myapplication.retrofit

import com.example.myapplication.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글톤 클래스
// 레트로핏 객체 생성 및 관리
object RetrofitInstance {

    // Retrofit 객체 초기화
    // 레트로핏 기본 사용법.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RetrofitController by lazy {
        retrofit.create(RetrofitController::class.java)
    }
}