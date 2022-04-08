package com.example.myapplication.example_study.repository

import com.example.myapplication.example_study.di.module.NetworkModule
import com.example.myapplication.example_study.dto.MaskDTO
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

// 인터페이스로 사용하는 이유 : 내부 구조는 자세히 몰라도 된다.
// DI 에서 인터페이스를 사용하는게 클래스보다 유연하다.

interface MaskService {
    @GET("sample.json")
    suspend fun fetchStoreInfo(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): Response<MaskDTO>

    companion object {
        // const val BASE_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/"
        // 공적마스크 API 중단으로 백업 API로 대체하였음
        const val BASE_URL = "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/"
    }
}