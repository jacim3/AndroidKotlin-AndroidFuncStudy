package com.example.myapplication.retrofit

import retrofit2.Response
import retrofit2.http.*


interface RetrofitService {
    @GET("posts/1")
    suspend fun getPosts(): Response<PostDTO>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<PostDTO>
}
