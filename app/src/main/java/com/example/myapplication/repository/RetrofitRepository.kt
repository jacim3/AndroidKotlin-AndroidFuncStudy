package com.example.myapplication.repository

import com.example.myapplication.retrofit.PostDTO
import com.example.myapplication.retrofit.RetrofitInstance
import retrofit2.Response


// 싱글톤으로 생성한 레트로핏 객체를 이용. 사용할 데이터에 관한 비즈니스 로직의 처리를 담당.
class RetrofitRepository {
    suspend fun getPost(): Response<PostDTO> {
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getPost2(number: Int): Response<PostDTO> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int) :Response<List<PostDTO>> {
        return RetrofitInstance.api.getCustomPosts(userId)
    }

    suspend fun getCustomPosts2(userId: Int, sort:String, order:String): Response<List<PostDTO>> {
        return RetrofitInstance.api.getCustomPosts2(userId, sort, order)
    }

    suspend fun getCustomPosts3(userId:Int, options: Map<String, String>) :Response<List<PostDTO>> {
        return RetrofitInstance.api.getCustomPosts3(userId, options)
    }
}