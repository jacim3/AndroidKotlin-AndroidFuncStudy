package com.example.myapplication.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Header

data class PostDTO(
    @SerializedName("userId") @Expose val userId: Int,
    @SerializedName("id") @Expose val id: String,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("body") @Expose val body: String,
)