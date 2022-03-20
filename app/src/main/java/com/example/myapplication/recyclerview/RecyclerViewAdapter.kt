package com.example.myapplication.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.retrofit.PostDTO

object RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var listData: List<PostDTO>

    // itemView 를 지정하는 뷰홀더 -> onCreateViewHolder 에서 필요.
    // 받아온 뷰 정보를 해석하는 역할 수행.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewId: TextView = view.findViewById(R.id.textViewId)
        var textViewUserId: TextView = view.findViewById(R.id.textViewUserID)
        var textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        var textViewBody: TextView = view.findViewById(R.id.textViewBody)
    }

    // ViewHolder 란 뷰를 보관하는 객체로서, 각 구성요소를 반복적으로 접근하는 과정을 간략화 함.
    // xml 에 지정된 뷰를 inflate 하며, 이를 해석하기 위하여 ViewHolder 클래스에 보내는 역할을 수행.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // onCreateViewHolder 를 통하여 해석된 ViewHolder 객체에 대하여 초기화를 담당.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]
        holder.textViewId.text = item.id
        holder.textViewUserId.text = item.userId.toString()
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.body
    }

    // 출력할 리스트의 갯수 결졍.
    override fun getItemCount(): Int {
        return listData.size
    }

    fun getListData(listData: List<PostDTO> ) {
        this.listData = listData
    }

    fun refreshData() {
        // notifyDataSetChanged() 는 최후의 수단이며, 자원낭비가 심함.
        // * payload:Object 는 onBindViewHolder()가 호출될 때 넘겨받음으로써,
        // 해당 위치에서 로직을 수행하기 위한 체크변수로써 기능을 수행할 수 있다.
        // notifyItemInserted(position) 해당 위치에 아이템 삽입
        // notifyItemRangeInserted(position, count) - 대량의 아이템 위치 삽입
        // notifyItemRemoved(position) - 아이템 삭제
        // notifyItemMoved(fromPosition, toPosition) - 아이템의 위치 변경. 항목이동 혹은 순위변경 같은로직에 사용
        // 특정 위치 아이템만 바꿔야 하는 경우 -> notifyItemChanged(position, payload)
        notifyItemRangeChanged(0, listData.size)
    }
}