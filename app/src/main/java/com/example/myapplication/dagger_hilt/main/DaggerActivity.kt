package com.example.myapplication.dagger_hilt.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R
import com.example.myapplication.dagger_hilt.data.MyRepository
import com.example.myapplication.dagger_hilt.di.ApplicationModule
import com.example.myapplication.databinding.ActivityDaggerBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


/*
    Dagger-Hilt
    안드로이드는 여러 컴포넌트가 존재 및 미리 준비되므로, DI 를 수행하는 방법이 기존의 자바 어플리케이션과 다르다.
    싱글톤을 통하여, 어디에서든 동일한 객체를 보장받으며, 유지보수 및 테스트가 쉬워진다.
    이를 구현하기 위해 기존 자아 어플리케이션 에서는 생성자 혹은 게터를 및 자동주입방식을 사용했으나,
    안드로이드 프레임워크내 클래스들은 이러한 방식을 사용할수 없다. (생성자를 개발자가 제어할 수 없음)

    프로젝트의 모든 클래스에 컨테이너를 제공하고, 라이프사이클을 자동으로 관리하여 DI 를 사용하는 표준을 제공

    Hilt 는 주석으로서 다음의 클래스에 종속항목을 제공할 진입점을 제공.
    1. @HiltAndroidApp - Application,
    2. @AndroidEntryPoint - Activity, Fragment, View, Service, BroadcastReceiver
    위의 어노테이션을 사용하면, 해당 클래스에 종속된 Android 클래스에도 주석을 지정해야 한다.

    3. @EntryPoint with @InstalledIn(~::class) : 콘텐트프로바이더와 같이 hilt 가 직접 지원하지 않는 클래스에
    필드 삽입을 수행하기 위해 입력.
    구성 요소에서 종속항목을 가져오기 위해. @Inject 를 통하여 필드삽입을 실행.
*/
                        // 개별 Hilt 구성요소 진입점 설정 @Inject 에 대한 의존성 주입 설정
                        // 여기서는 해당 선언을 통하여 SingletonComponent 의 하위 컴포넌트인 ActivityComponent 가 생성된다.
                        // * 스프링에서 컴포넌트는 IOC 컨테이너에 Bean 을 등록하도록 메타데이터를 기입하는 어노테이션
                        //   @Bean 은 개발자가 제어하기 힘든 외부라이브러리를 Bean 으로 만들기 위해
@AndroidEntryPoint      //   @Component 는 개발자가 직접 작성한 Class 를 직접 Bean 에 등록하기 위해
class DaggerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDaggerBinding

    private val viewModel by viewModels<DaggerViewModel>()
    @Inject
    lateinit var repository: MyRepository

    @ApplicationModule.AppHash
    @Inject
    lateinit var applicationHash:String
    // Inject 는 타입을 추론하여 동작한다.

    @ApplicationModule.BppHash
    @Inject
    lateinit var application:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dagger)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_dagger)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Log.e("DaggerActivity", repository.hashCode().toString())
        Log.e("DaggerActivity", applicationHash)
        Log.e("DaggerActivity", application)
        Log.e("DaggerActivity", viewModel.getRepositoryHash())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_dagger)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}