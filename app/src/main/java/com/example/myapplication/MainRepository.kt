package com.example.myapplication

import com.example.myapplication.retrofit.PostDTO
import com.example.myapplication.retrofit.RetrofitInstance
import retrofit2.Response

class MainRepository {
    suspend fun getPost() : Response<PostDTO>{
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getPost2(number:Int) : Response<PostDTO> {
        return RetrofitInstance.api.getPost2(number)
    }
}