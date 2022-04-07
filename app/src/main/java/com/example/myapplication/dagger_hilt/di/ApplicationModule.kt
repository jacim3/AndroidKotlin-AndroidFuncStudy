package com.example.myapplication.dagger_hilt.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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

@Module
@InstallIn(ActivityComponent::class)
object ApplicationModule {
    @AppHash
    @Provides
    fun provideHash():String = hashCode().toString()

    @BppHash
    @Provides
    fun provideHa():String = "aaaaaa"

    // ----------- 위의 등록된 객체를 처리하기 위한 어노테이션 생성 ------------- //

    @Qualifier                                 // hilt 에서 어노테이션 생성
    @Retention(AnnotationRetention.RUNTIME)    // 자바에서 커스텀 어노테이션 생성 방식
    annotation class AppHash

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BppHash

}