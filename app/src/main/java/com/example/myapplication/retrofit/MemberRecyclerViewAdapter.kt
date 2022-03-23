package com.example.myapplication.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.recyclerview.RecyclerViewAdapter
import retrofit2.Response

class MemberRecyclerViewAdapter : RecyclerView.Adapter<MemberRecyclerViewAdapter.ViewHolder>() {
    private lateinit var memberListData: List<MemberDTO.MemberInfoDTO>

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewProfile: AppCompatImageView = view.findViewById(R.id.imageViewProfile)
        val textViewName: AppCompatTextView = view.findViewById(R.id.textViewName)
        val textViewEmail: AppCompatTextView = view.findViewById(R.id.textViewEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.retrofit_recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memberItem = memberListData[position]
        val name = "${memberItem.firstName} ${memberItem.lastName}"
        // Glide 를 통하여 URL 형태로 제공된 프로필 이미지 삽입
        Glide.with(holder.itemView).load(memberItem.profileImage).into(holder.imageViewProfile)
        holder.textViewName.text = name
        holder.textViewEmail.text = memberItem.email
    }

    override fun getItemCount(): Int {
        return memberListData.size
    }

    fun getMemberListData(memberListData: List<MemberDTO.MemberInfoDTO>) {
        this.memberListData = memberListData
    }

    fun refreshData() {
        notifyItemRangeChanged(0, memberListData.size)
    }
}