package com.example.myapplication.example_study.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMaskItemBinding
import com.example.myapplication.example_study.dto.MaskDTO
import com.example.myapplication.example_study.dto.Store

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.Companion.StoredViewHolder>() {

    lateinit var items: List<MaskDTO.Stores>

    companion object {
        class StoredViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            // 바인딩 객체 생성
            val binding = ActivityMaskItemBinding.bind(view)

            val textViewTitle: AppCompatTextView = view.findViewById(R.id.textViewTitle)
            val textViewAddress: AppCompatTextView = view.findViewById(R.id.textViewAddress)
            val textViewDistance: AppCompatTextView = view.findViewById(R.id.textViewDistance)
            val textViewCount: AppCompatTextView = view.findViewById(R.id.textViewCount)
            val textViewStatus: AppCompatTextView = view.findViewById(R.id.textViewStatus)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoredViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.activity_mask_item, parent, false)
        return StoredViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoredViewHolder, position: Int) {
        val item = items[position]
        holder.binding.store = items[position]
//        var remainStat = ""
//        var remainCount = ""
//        var color = Color.GREEN

/*
        when (item.remain_status) {
            "plenty" -> {
                remainStat = "100개 이상"
                remainCount = "충분"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "30개 이상"
                remainCount = "여유"
                color = Color.YELLOW
            }
            "empty" -> {
                remainStat = "재고 없음"
                remainCount = "없음"
                color = Color.GRAY
            }
            "few" -> {
                remainStat = "2개 이상"
                remainCount = "매진임박"
                color = Color.RED
            }
            else -> {

            }
        }*/


        holder.textViewTitle.text = item.name
        holder.textViewAddress.text = item.address
        //holder.textViewCount.text = remainStat
        //holder.textViewCount.setTextColor(color)
        holder.textViewDistance.text = String.format("%.2fkm", item.distance)
        //holder.textViewStatus.text = remainCount
        //holder.textViewStatus.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

@BindingAdapter("remainStat")
fun setRemainStat(textView: AppCompatTextView, item: MaskDTO.Stores) {
    when (item.remain_status) {
        "plenty" ->
            textView.text = "충분"
        "some" ->
            textView.text = "여유"
        "empty" ->
            textView.text = "없음"
        else ->
            textView.text = "매진임박"
    }
}

@BindingAdapter("count")
fun setCount(textView: AppCompatTextView, item: MaskDTO.Stores) {
    when (item.remain_status) {
        "plenty" ->
            textView.text = "100개 이상"
        "some" ->
            textView.text = "30개 이상"
        "empty" ->
            textView.text = "재고 없음"
        else ->
            textView.text = "2개 이상"
    }
}

@BindingAdapter("color")
fun setColor(textView: AppCompatTextView, item: MaskDTO.Stores) {
    when (item.remain_status) {
        "plenty" ->
            textView.setTextColor(Color.GREEN)
        "some" ->
            textView.setTextColor(Color.YELLOW)
        "empty" ->
            textView.setTextColor(Color.GRAY)
        else ->
            textView.setTextColor(Color.RED)
    }
}
