package com.example.myapplication.example_study.di.module

import com.example.myapplication.example_study.repository.MaskService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)        // 굳이 @SingleTon 을 지정하지 않아도 된다.
object NetworkModule {

    @Network
    @Provides
    fun provideMaskService(): MaskService {
        val retrofit = Retrofit.Builder().baseUrl(MaskService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MaskService::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Network
}