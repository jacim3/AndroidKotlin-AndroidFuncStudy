package com.example.myapplication.dagger_hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



/*
    Hilt 를 사용하는 모든 앱은 어노테이션이 지정된 클래스를 포함해야 한다.
    application 객체의 수명주기에 연결되어, 이와 관련한 종속항목을 제공


*/
@HiltAndroidApp
class App: Application() {
}