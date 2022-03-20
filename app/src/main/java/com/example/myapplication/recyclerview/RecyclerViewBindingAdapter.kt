package com.example.myapplication.recyclerview

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.retrofit.PostDTO
import retrofit2.Response

object RecyclerViewBindingAdapter {
    /* Databinding 과 연결하여, 리사이클러뷰 관리
       리사이클러뷰 xml app:items 에 등록하여 처리
    */
//    @BindingAdapter("items")
//    @JvmStatic
//    fun setItems(recyclerView: RecyclerView, items: List<PostDTO>) {
//
//        if(recyclerView.adapter == null)
//            recyclerView.adapter = RecyclerViewAdapter(items)
//
//        val adapter = recyclerView.adapter as RecyclerViewAdapter
//
//        adapter.listData = items
//        //adapter.notifyItemRangeChanged(0, items.value?.body()!!. size-1)
//
//    }
}