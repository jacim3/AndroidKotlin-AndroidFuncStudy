package com.example.myapplication.retrofit

import retrofit2.Response
import retrofit2.http.*

// 레트로핏에서 컨트롤러 역할을 담당
interface RetrofitController {

    // 직접 입력
    @GET("posts/1")
    suspend fun getPosts(): Response<PostDTO>

    // 변수 대입
    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<PostDTO>

    //단일 쿼리
    @GET("posts")
    suspend fun getCustomPosts(
        @Query("userId") userOd : Int
    ):Response<List<PostDTO>>
    
    // 다중 쿼리
    @GET("posts")
    suspend fun getCustomPosts2(
        @Query("userId") userId:Int,
        @Query("_sort") sort:String,
        @Query("_order") order:String
    ):Response<List<PostDTO>>

    // 다중쿼리 - 쿼리맵
    //https://jsonplaceholder.typicode.com/posts?userId=1&order=desc&_sort=id 이런식으로 대입
    @GET("posts")
    suspend fun getCustomPosts3(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ):Response<List<PostDTO>>

}
