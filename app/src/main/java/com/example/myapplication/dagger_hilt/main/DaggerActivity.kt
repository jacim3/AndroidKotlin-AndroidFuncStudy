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
    이를 구현하기 위해 생성자 혹은 게터를 통한 주입방식을 사용하나,안드로이드 프레임워크 내 몇몇 컴포넌트 들은 이러한
    방식을 사용할수 없다.

    프로젝트의 모든 클래스에 컨테이너를 제공하고, 라이프사이클을 자동으로 관리하여 DI 를 사용하는 표준을 제공

*/

@AndroidEntryPoint      // 개별 Hilt 구성요소 진입점 설정
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