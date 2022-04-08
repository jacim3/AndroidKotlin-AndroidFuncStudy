package com.example.myapplication.example_study.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.example_study.di.module.NetworkModule
import com.example.myapplication.example_study.dto.LatLng
import com.example.myapplication.example_study.dto.MaskDTO
import com.example.myapplication.example_study.repository.MaskService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.Comparator

@HiltViewModel
class MaskViewModel @Inject constructor(): ViewModel() {
    val items = MutableLiveData<List<MaskDTO.Stores>>()
    val latLng = MutableLiveData<LatLng>()
    val isLoadingCompleted = MutableLiveData<Boolean>()

    @NetworkModule.Network
    @Inject
    lateinit var service: MaskService
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadData() {
        viewModelScope.launch {

            isLoadingCompleted.value = true
            val response = service.fetchStoreInfo(latLng.value!!.latitude, latLng.value!!.longitude)

            if (response.isSuccessful) {
                val item: MutableList<MaskDTO.Stores>? = response.body()!!.stores.stream().filter {
                    it.remain_status == null ||
                    it.remain_status.trim() == "" ||
                    it.remain_status != "empty"
                }.collect(Collectors.toList())
                Log.e("loadData", item!!.size.toString())
                for (i in item.indices) {

                    item[i].distance = LocationDistance.distance(
                        latLng.value!!.latitude, latLng.value!!.longitude,
                        item[i].lat, item[i].lng, "k"
                    )
                }

                // Comparable 을 상속한 클래스에서 Collections.sort() 를 통하여 정렬을 수행하는 것으로도 동작 가능!!!
                val subItem =
                    item.stream().sorted { p0, p1 -> p0!!.distance.compareTo(p1!!.distance) }
                        .collect(Collectors.toList())
                items.value = subItem
                isLoadingCompleted.value = false
            } else {
                items.value = Collections.emptyList()       // null 대신 빈 리스트 세팅
                isLoadingCompleted.value = false
            }
        }
    }
}