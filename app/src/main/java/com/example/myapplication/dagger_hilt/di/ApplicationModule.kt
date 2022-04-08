package com.example.myapplication.dagger_hilt.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


/*
    Application	                                SingletonComponent      	    @Singleton
    Activity	                                ActivityRetainedComponent	    @ActivityRetainedScoped
    ViewModel	                                ViewModelComponent      	    @ViewModelScoped
    Activity	                                ActivityComponent	            @ActivityScoped
    Fragment	                                FragmentComponent	            @FragmentScoped
    View	                                    ViewComponent	                @ViewScoped
    View annotated with @WithFragmentBindings	ViewWithFragmentComponent	    @ViewScoped
    Service                                 	ServiceComponent	            @ServiceScoped
*/

/*
    Hilt Module 의 사용 이유
    생성자 삽입이 불가능한 경우 (인터페이스, 외부 라이브러리 클래스 등) 해당 모듈을 이용하여 결합 정보를 제공한다.

*/

/*
    구성요소 기본결합
    서브클래스를 제외한 액티비티와 프래그먼트 유형에 해당하는 Hilt 바인딩 세트

    Android 구성요소	                기본 결합
    SingletonComponent	            Application
    ActivityRetainedComponent	    Application
    ActivityComponent	            Application, Activity
    FragmentComponent	            Application, Activity, Fragment
    ViewComponent               	Application, Activity, View
    ViewWithFragmentComponent   	Application, Activity, Fragment, View
    ServiceComponent            	Application, Service
*/

/*  각 Android 컴포넌트 클래스에서 사용하는 Hilt 컴포넌트
    SingletonComponent	        Application
    ActivityRetainedComponent	ViewModel
    ActivityComponent	        Activity
    FragmentComponent	        Fragment
    ViewComponent           	View
    ViewWithFragmentComponent	@WithFragmentBindings 주석이 지정된 View
    ServiceComponent	        Service
*/
@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @AppHash
    @Provides
    fun provideHash():String = hashCode().toString()

    @BppHash
    @Provides
    fun provideHa():String = "aaaaaa"

/*
     위의 등록된 객체에 대한 주입을 명시하기 위한 어노테이션 생성 ---
     일반적으로 주입은 반환자료형을 기준으로 자동 설정된다. 그러므로 이러한 자료형이 중복될 경우
     어노테이션을 통하여 구체적으로 주입 대상을 명시해야 빌드 오류가 생기지 않는다.

     @ApplicationContext, @ActivityContext 와 같은 Hilt 에서 기본 제공하는 한정자는 application,
     activity 의 Context 정보를 필요로 할 수 있으므로, 이를 알리기 위해 사용
*/

    @Qualifier                                 // hilt 에서 어노테이션 생성
    @Retention(AnnotationRetention.RUNTIME)    // 자바에서 커스텀 어노테이션 생성 방식
    annotation class AppHash

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BppHash

}