package com.example.myapplication.reactive

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class GithubClient {
    companion object {
        private const val BASE_URL = "https://api.github.com"

        fun getApi(): GithubService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }
}

data class GithubRepo(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val date: String,
    @SerializedName("html_url") val url: String
)

interface GithubService {
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner: String) : Single<List<GithubRepo>>
}


