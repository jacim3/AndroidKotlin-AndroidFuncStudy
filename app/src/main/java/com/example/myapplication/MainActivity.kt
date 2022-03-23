package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.main_fragment.MainFragment
import com.example.myapplication.repository.RetrofitRepository
import kotlin.system.exitProcess

/*
* 아키텍쳐 패턴 : 시스템을 구성하는 서브 시스템, 컴포넌트와 같이 구성요소 간의 관계를 관리하는 시스템의 구조
* 디자인 패턴 : 아키텍쳐보단 좁은 개념. 프로그램 개발 과정속에서 자주 나타나는 문제들을 쉽게 해결하기 위한 방법으로서,
  과거의 소프트웨어 개발 과정에서 발견된 설계의 노하우를 바탕으로 이후에도 재사용할수 있기 좋은 형태로 가공하여 정리한것.

Android 앱에는 많은 구성요소를 포함할 수 있으므로, 사용자 중심의 다양한 작업에 맞게 조정될 수 있어야 한다.
하지만, 휴대기기의 리소스는 한정되어 있고, 수시로 운영체제에 의하여 정리 (앱 프로세스 종료) 가 될 수 있으므로,
앱 구성요소에 애플리케이션 데이터나 상태를 저장 혹은 구성요소 서로 간 종속되어서는 안됨.

이러한 아키텍쳐 구현을 위하여 관심사를 분리 (앱의 부분과 그 각 부분에 필요한 기능간의 경계를 정의)

1. Activity, Fragment 와 같은 UI 기반 클래스는 UI및 운영체제 상호작용 로직만 포함할 것.
이들은 OS와 앱 사이 계약을 나타내도록 이어주는 클래스일 뿐, OS나 사용자의 입력에 의하여 언제든지 제거될 수 있음.
즉, 클래스 의존성을 최소화 해야 한다.

2. 데이터 모델에서 UI를 도출해야 한다. 이들은 앱의 UI 요소 및 기타 구성요소와 독립되어 있다.
2.1 Android OS 에서 리소스 확보를 위하여 앱을 종료시켜도 사용자 데이터를 잃지 않는다.
2.2 네트워크가 불안정 하더라도 이용할 수 있다. (각각의 컴포넌트가 의존성이 최소화되어, 각각의 작업을 수행하므로)

기본 앱 아키텍쳐 구조.
1. UI Layer (프리젠테이션 레이어) - 뷰와 데이터의 소유권 분리.
1.1 화면에 데이터를 렌더링하는 UI 요소 (View, Jetpack Compose)
1.2 데이터를 보유하고, 이를 UI에 노출하며 로직을 처리하는 상태 홀더 (ViewModel)

2. 도메인 레이어 (UI 레이어와 데이터레이어 사이에 있는 선택적 레이어)
2.1 복잡한 비즈니스 로직 or 여러 VIewModel 에서 재사용되는 비즈니스 로직의 캠슐화를 담당.
2.2 선택사항. 복잡성 처리, 재사용성을 선호한다면 추가로 구현.

3. Data Layer
3.1 비즈니스 로직(앱의 데이터 생성, 저장, 변경방식을 결정하는 규칙)이 포함
3.2 데이터 레이어는 여러개의 데이터 소스를 각각 포함할 수 있는 Repository 클래스 로 구성. 앱에서 처리하는 다양한
유형의 데이터 마다 저장소 클래스를 만들것을 권장. (파일, 네트워크 소스, 로컬데이터베이스 등 구조의 데이터)


*/

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var mainViewModel: MainViewModel
    // onSaveInstanceState
    // 파라미터 1개 : onPause() 다음에 무조건 실행
    // 파라미터 2개 : onPause() 다음에 화면 전환같은 상황에서 액티비티가 종료된 것인지 판단하여 실행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = RetrofitRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding!!.lifecycleOwner = this

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding!!.mainViewModel = mainViewModel
        setSupportActionBar(binding!!.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // retrofit 네트워크작업을 ViewModel 에서 실행
        mainViewModel.getPost()
        mainViewModel.myResponse.observe(this, Observer {

//            if (it.isSuccessful) {
//                Log.d("Response", it.body()!!.userId.toString())
//                Log.d("Response", it.body()!!.id)
//                Log.d("Response", it.body()!!.title)
//                Log.d("Response", it.body()!!.body)
//            } else {
//                Log.d("Response", it.errorBody().toString())
//            }
        })

        mainViewModel.alarmAndBootCheck(applicationContext)
        supportFragmentManager.beginTransaction().replace(MAIN_Container, MainFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            android.R.id.home -> supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (savedInstanceState != null) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    // object 로 성언된 클래스와는 초기화 시점이 다르다.
    // 1. 클래스에 들어가는 블럭이므로, 그 자체는 static이 아님
    // 2. 해당 클래스가 로드될 때, 초기화됨
    // 3. const val 로 선언된 상수는 static 변수
    // 4. @JVMStatic or @JVAField or const val 로 선언되지 않은 멤버변수를은 static 변수가 아니다.
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val REQ_RES = "https://reqres.in/"
        const val MAIN_Container = R.id.main_layout
        const val NUM_TABS = 3
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exitProcess(0)
    }

    // 여기서 데이터 저장
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
}