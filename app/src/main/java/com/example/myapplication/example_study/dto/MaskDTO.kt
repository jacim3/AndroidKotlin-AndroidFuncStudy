package com.example.myapplication.example_study.dto

import com.google.gson.annotations.SerializedName

data class MaskDTO(
    @SerializedName("count") val count: Int,
    @SerializedName("stores") val stores: List<Stores>
) {
    data class Stores(
        @SerializedName("addr") val address: String,
        @SerializedName("code") val code: Int,
        @SerializedName("created_at") val created_at: String,
        @SerializedName("lat") val lat: Double,
        @SerializedName("lng") val lng: Double,
        @SerializedName("name") val name: String,
        @SerializedName("remain_stat") val remain_status: String?,
        @SerializedName("stock_at") val stocked_at: String,
        @SerializedName("type") val type: Int,
        @SerializedName("distance") var distance: Double
    )
}

data class Store(
    var address: String,
    var code: Int,
    var created_at: String,
    var lat: Double,
    var lng: Double,
    var name: String,
    var remain_status: String?,
    var stocked_at: String,
    var type: Int,
    var distance: Double
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)