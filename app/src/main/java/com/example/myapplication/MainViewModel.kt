package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.broadcast_receiver.BootReceiver
import com.example.myapplication.repository.RetrofitRepository
import com.example.myapplication.retrofit.PostDTO
import com.example.myapplication.viewpager2.ViewpagerFragment
import kotlinx.coroutines.launch
import retrofit2.Response


// ViewModel - 수명주기를 고려하여, UI를 위한 데이터를 저장하고 관리
// 뷰와 분리되어 있으므로, 액티비티 및 프래그먼트가 파괴되고 다시 생성되어도 데이터를 여전히 보유
// * onSaveInstanceState() 메서드를 통하여, onCreate()에서 다시 데이터를 복구하는 방법도 있으나,
// 비트맵등의 대룡량 데이터가 아닌, 직렬화-역직렬화가 가능한 소량에 데이터애만 적합.
// ViewModel 의 생명주기는, ViewModel 생성 시, ViewModelProvider()에 전달하는 lifeCycle 인자에
// 의해 지정되며, 액티비티, 프래그먼트 종료시 까지 메모리에 유지된다.
// 뷰모델이 종료됨에 따른 onCleared() 콜백을 사용할 수 있으므로, 이를 오버라이드 하여, 리소스 정리 로직은
// 여기에 작성한다.

// 뷰모델에서 어플리케이션 컨텍스트 정보를 사용하고 싶으면, ViewModel 대신 AndroidViewModel 을 상속.
// 어플리케이션 컨텍스트는 액티비티의 생명주기 대신 어플리케이션 생명주기를 따르므로, 이러한 정보를 참조하는 것까지는 뷰모델에서
// 허용한다.
class MainViewModel(private val repository: RetrofitRepository) : ViewModel() {

    // LiveData : Observable 데이터 홀더 클래스.
    // View 에서 ViewModel 의 라이브데이터를 관찰하게 되면, 데이터 변경이 이루어질 때, 이를 알려준다.
    // 애기비티와 프래그먼트의 생명주기를 인지하고, 이들이 활성화될 때만, UI 변경등이 동작하도록 하고,
    // Destroy() 이후는 동작하지 않도록 함으로써 메모리 누수를 막아준다.
    val myResponse = MutableLiveData<Response<PostDTO>>()
    val myResponse2 = MutableLiveData<Response<PostDTO>>()
    val myCustomPosts: MutableLiveData<Response<List<PostDTO>>> = MutableLiveData()

    // ViewModel 에서 간편하게 코루틴을 호출하는 방법
    // Coroutine 의 관리 단위는 Scope 이며,
    // Scope 종료 시 그 안의 Coroutine 도 같이 종료한다.
    // 네크워크가 종료된 이후, 해당 비동기 코드도 함께 종료하기 위함.
    // launch 내부에서 비동기 작업 처리.
    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number: Int) {
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }

    fun getCustomPosts(userId: Int) {
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId)
            myCustomPosts.value = response
        }
    }

    fun getCustomPosts2(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response = repository.getCustomPosts2(userId, sort, order)
            myCustomPosts.value = response
        }
    }

    fun getCustomPosts3(userId: Int, option: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getCustomPosts3(userId, option)
            myCustomPosts.value = response
        }
    }

    // 앱 실행 시 부트 리시버 보내놓기. -> 세부 로직은 BootReceiver 클래스에서 수행.
    fun alarmAndBootCheck(context: Context) {
        val packageManager = context.packageManager
        val bootReceiver = ComponentName(context, BootReceiver::class.java)
        packageManager.setComponentEnabledSetting(
            bootReceiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
        Log.e("BootReceiverRegistered", "!@#!@#")
    }

    fun moveToFragment(fragmentManager: FragmentManager, index: Int) {

        val step = arrayOf(
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
            ViewpagerFragment(),
        )
        val mainLayout = R.id.main_layout
        fragmentManager.beginTransaction().replace(mainLayout, step[index]).commit()
    }

}