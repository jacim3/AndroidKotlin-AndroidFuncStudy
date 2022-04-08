package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/*
    Hilt 는 애플리케이션에서 DI 를 사용하는 표준적인 방법을 제공
    1. Dagger 사용의 단순화
    2. 표준화된 컴포넌트 세트와 스코프로 설정과 가독성/이해도 쉽게
    3. 쉬운 방법으로 다양한 빌드타입에 대해 다른 바인딩 제공
    4. 컴포넌트들은 계층으로 이루어지며, 하위 컴포넌트는 상위컴포넌가 가진 의존성에 접근 가능 (직계관계일 때만:SubComponent)
    Hilt 를 사용하는 모든 앱은 @HiltAndroidApp 으로 주석이 지정된 Application 클래스를 포함해야 한다.
    앱의 상위 구성요소인 application 객체의 수명주기에 연결되어, 이와 관련한 종속항목을 제공

    DI 의 특징
    - 클래스간 결합도를 낮춘다
    - 인터페이스 기반으로 설계되며, 코드를 유연하게 한다.
    - Stub 또는 Mock 객체를 사용하여 단위테스트를 하기가 더욱 쉬워진다.

    안드로이드의 클래스들은 안드로이드 프레임워크에 의하여 인스턴스화 하므로, 클래스 내부에 생성자를 만들거나,
    매개변수를 전달하는 방법이 불가능하여, 의존성주입 구현이 어렵다.
    LifeCycle 와 계층별로 잘 정리된 객체들을 공유할 수 있는 방법을 제공.
    같은결과에 대해 다양한 방법이 존재.
*/


                // 애플리케이션 수준 종속항목 컨테이너 역할. 모든 의존성 주입의 시작점 이를 선언하여, SingletonComponent 가 생성된다.
                // 컴파일 시 'Hilt_' 라는 접두어가 붙은 어플리케이션 클래스를 생성한다. 생성된 클래스는 Base클래스가
                // 상속한 클래스를 모두 똑같이 상속함 컴프넌트 생성 및 주입코드를 여기에 모두 포함.
                // 이 과정 이후 @AndroidEntryPoint 를 사용할수 있게 되고, 해당 어노테이션이 추가된 안드로이드 클래스에
@HiltAndroidApp // DI 컨테이너를 추가한다. 즉, 이 과정은 컴포넌트를 생성. 엔트리포인트는 서브컴포넌트를 생성
class Applications:Application() {

    override fun onCreate() {
        super.onCreate()        // bytecode 변환을 통하여 컴포넌트를 인스턴스화
    }
}

/*
    * ComponentHierarchy and HiltScope
    힐트는 ModuleClass 에서 표준화된 @Scope 를 통하여 동일 인스턴스를 공유할 수 있다.
    TODO 모듈에서 사용되는 Scope 어노테이션은 반드시 @InstallIn 에 명시된 컴포넌트와 쌍을 이루는 스코프를 사용해야 한다.!

           SingletonComponent
           * (Singleton)
                    |        \
    ActivityRetainedComponent  ServiceComponent
    * (ActivityRetainedScope)  * (ServiceScoped)
                    |
            ActivityComponent
            * (ActivityScoped)
                    |        \
            FragmentComponent  ViewComponent
            * (FragmentScoped) * (ViewScoped)
                    |
    ViewWithFragmentComponent
    * (ViewScoped)

*/

/*
    * Hilt Scope


*/