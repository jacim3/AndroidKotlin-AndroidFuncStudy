package com.example.myapplication.recyclerview

import android.app.AlertDialog
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.retrofit.PostDTO
import com.example.myapplication.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class RecyclerViewViewModel : ViewModel() {

    val listData:MutableLiveData<Response<List<PostDTO>>> = MutableLiveData()

    fun searchData(cursor:Int, order:HashMap<String, String>, dialog: AlertDialog) {
        viewModelScope.launch {
            dialog.show()
            listData.value = RetrofitInstance.api.getCustomPosts3(cursor, order)
        }
    }
}