package com.example.myapplication.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemberDTO(
    @SerializedName("data")
    @Expose
    val memberList: List<MemberInfoDTO>
) {
    data class MemberInfoDTO(
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("email")
        @Expose
        val email:String,
        @SerializedName("first_name")
        @Expose
        val firstName:String,
        @SerializedName("last_name")
        @Expose
        val lastName:String,
        @SerializedName("avatar")
        @Expose
        val profileImage:String
    )
}



