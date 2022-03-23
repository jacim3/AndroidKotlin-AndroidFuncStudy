package com.example.myapplication.retrofit

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RetrofitViewModel : ViewModel() {
    // 라이브 데이터 홀더.
    val memberListData = MutableLiveData<
            List<MemberDTO.MemberInfoDTO>>()

    fun getMemberList(page: Int, dialog: AlertDialog) {
        viewModelScope.launch {
            dialog.show()
            val response = RetrofitInstance.api2.getUserList(page)

            memberListData.value = response.body()?.memberList
        }
    }

    fun toNextPage(context: Context, cursor:Int, dialog: AlertDialog) :Int{
        return if (cursor > 2) {
            Toast.makeText(context, "다음페이지는 없습니다.", Toast.LENGTH_SHORT).show()
            2
        } else {
            getMemberList(cursor, dialog)
            cursor
        }
    }

    fun toPrevPage(context: Context, cursor:Int, dialog: AlertDialog) : Int {
        return if (cursor <= 0) {
            Toast.makeText(context, "이전페이지는 없습니다.", Toast.LENGTH_SHORT).show()
            1
        } else {
            getMemberList(cursor, dialog)
            cursor
        }
    }
}