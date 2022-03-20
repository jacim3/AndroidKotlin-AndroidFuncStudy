package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.RetrofitRepository


/*
팩토리 패턴
- 클래스의 인스턴스를 만드는 것을 서브클래스에서 결정하도록 함으로써, 객체 생성을 캡슐화하고,
  구상 클래스(new 키워드와 함께 오출하여 인스턴스를 생성하는 클래스)에 대한 의존성이 줄어든다.
- 팩토리 메서드 패턴과 추상 팩토리 패턴이 존재
- Dependency Inversion 원칙에 기인하여 Dependency Injection 을 구현.

* 팩토리 메소드 패턴 : 객체를 생성하기 위한 인터페이스를 정의하는데, 어떤 클래스의 인스턴스를 만들지는 서브 클래스에서 결정한다.
* 추상 팩토리 패턴 : 인터페이스를 이용하여 서로 연관된, 또는 의존하는 객체를 구상 클래스를 지정하지 않고도 생성할 수 있다.
  추상 팩토리 패턴에는 팩토리 메소드 패턴이 포함될 수 있다.
- 결합도 (클레스에 변경사항에 따른 다른클래스의 영향)
*/
class MainViewModelFactory(private val repository: RetrofitRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
