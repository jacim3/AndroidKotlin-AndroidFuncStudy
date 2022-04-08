package com.example.myapplication.example_study.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMaskBinding
import com.example.myapplication.example_study.dto.LatLng
import com.example.myapplication.example_study.dto.MaskDTO
import com.example.myapplication.example_study.repository.MaskService
import com.example.myapplication.recyclerview.RecyclerViewAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.stream.Collectors

@AndroidEntryPoint
class MaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityMaskBinding
    lateinit var maskViewModel: MaskViewModel
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mask)
        maskViewModel = ViewModelProvider(this)[MaskViewModel::class.java]

        binding.maskRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = StoreAdapter()

        // 퍼미션 허용 콜백 등록
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { _ ->
            startLocationAfterPermissionCheck()
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        // 퍼미션 요청 수행!! 앱 시작 시 항상 보내도록 한다. -> 디바이스 설정에서 퍼미션 허용상태를 변경할 수 있음.
        // 위에 등록한 registerForActivityResult 에 요청이 보내진다.
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        maskViewModel.latLng.observe(this){
            Log.e("LoadData","LoadData")
            maskViewModel.loadData()
        }

        maskViewModel.items.observe(this) {
            binding.maskRecyclerView.adapter = adapter
            adapter.items = it
        }

        maskViewModel.isLoadingCompleted.observe(this){

            if (it) {
                binding.progressBarLoading.visibility = View.VISIBLE
            } else {
                binding.progressBarLoading.visibility = View.INVISIBLE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun startLocationAfterPermissionCheck(){
        val src = CancellationTokenSource()
        val ct: CancellationToken = src.token

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "퍼미션을 허용해야 앱 이용이 가능합니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
        fusedLocationProviderClient.getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            ct
        ).addOnSuccessListener {
            if (it !== null) {
                Log.e("asdfasdf", "${it.latitude} ${it.longitude}")
                maskViewModel.latLng.value = LatLng(it.latitude, it.longitude)

            }
        }
    }
}



