package com.example.myapplication.retrofit

import com.example.myapplication.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// java 의 static 선언과는 다르다.
// 1. SingleTon 형태
// 2. Thread-Safe
// 3. Lazy initialized
// 4. @JVMStatic or @JVAField or const val 로 선언되지 않은 멤버변수를은 static 변수가 아니다.
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

    // reqRes 와 통신
    private val retrofit2: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(MainActivity.REQ_RES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api2: RetrofitController by lazy {
        retrofit2.create(RetrofitController::class.java)
    }


}