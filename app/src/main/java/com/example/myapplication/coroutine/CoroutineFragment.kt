package com.example.myapplication.coroutine

import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.CoroutineFragmentBinding
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

/*
* 코루틴은 비동기적으로 실행되는 코드를 간소화 하기 위행 Android 에서 사용할 수 있는 동시 설계 패턴.
* 기본스레드를 차단하여 앱이 응답하지 않게 만들 수도 있는 복잡한 작업을 관리하는데 도움. 코루틴을 가벼운 쓰레드로
* 생각할 수 있으나, 특정 쓰레드를 바인딩 하지 않고 일시정지를 활용한 단일 쓰레드 동시설계 패턴이므로, 이 점이 쓰레드와 구분된다.
*
* 1. 경량 : 코루틴을 실행중인 쓰레드를 차단하지 않는 정지를 지원하므로, 단일 쓰레드에서 많은 코루틴을 실행할수 있다.
*
* 2. 메모리 누수 감소 : 구조화된 동시 실행을 사용하여 범위 내에서 작업한다.
* 모든 코루틴은 스코프 내에서 실행되어야 하며, 액티비티가 소멸될 때, 관련 코루틴을 한번에 취소함으로싸. 메모리 누수 방지가 가능
* 코루틴 스코프는 안드로이드 내 3가지가 존재
* 2.1 글로벌 스코프 : 앱의 생명주기와 함께 동작하기 때문에 실행 도중에 별도 생명주기가 필요없음. 앱의 시작~ 종료 전 까지
* 장기간 실행하는 스코프에 적합
* 2.2 코루틴 스코프 : 버튼을 눌러, 다운로드 하거나 서버에서 이미지를 열 때 등 필요할때 호출하는 작업에 적합.
* 2.3 ViewModelScope : 뷰모델 컴포넌트 사용 시, ViewModel 인스턴스에서 사용하기 위한 스코프.
* 해당 스코프로 실행되는 코루틴은 뷰모델 인스턴스가 소멸할 대, 자동으로 취소.
*
* 3. 기본으로 제공되는 취소 지원 : 실행 중인 코루틴 게층 구조를 통해 자동으로 취소가 전달됨.
*
* 4. Jetpack 통합
*
* 코루틴의 디스페처 : 코루티을 적당한 스레드에 할당하며, 코루틴 실행 도중 일시정지 or 실행재개를 담당.
* 1. Dispatchers.Default : 안드로이드 기본 스레드풀 사용. CPU 를 많이 쓰는 작업에 최적화
* 2. Dispatchers.IO : 이미지 다운로드, 파일 입출력 등 입출력에 최적화 되어있는 디스페처 (네트워크, 디스크, DB)
* 3. Dispatchers.Main : 안드로이드 기본 스레드에 코루틴 실행. UI와 상호작용에 최적화
* 4. Dispatchers.Unconfined : 호출한 컨텍스트를 기본으로 상요하는데, 중단 후 다시 실행될 때 바귄 컨텍스트를 따라가는 디스페처
* */

/*
     코루틴이란 새로운 개념이 아닌 JavaScript 의 await async 와 같이 기존에 존재하던 프로그래밍 개념
     1. 협력형 멀티태스킹 (co : 함께, routine : 업무, 함수)
     2. 동시성 프로그래밍 지원
     3. 비동기 처리를 쉽게 도와줌
*/

class CoroutineFragment : Fragment(), CoroutineScope {

    private lateinit var viewModel: CoroutineViewModel
    private var binding: CoroutineFragmentBinding? = null
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()
    }

    private fun loadReverseGeoInformation() {
        launch(coroutineContext) {
            try {
                withContext(Dispatchers.IO) {

                }

            }catch (e: Exception) {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CoroutineFragmentBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoroutineViewModel::class.java]

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        //fun newInstance() = CoroutineFragment()
    }

}

// 메인 루틴
/*    fun main() {
        // 일반적인 함수에선 루틴에 진입하는 지점과 종료되는 지점이 명확
        val addedValue = example(2)
        drawPerson()
        // 그러나 코루틴에선,
    }

    // 서브 루틴
    fun example(value: Int) :Int {
        val one = 1
        val addedValue = value+one

        return addedValue
    }

    fun drawPerson() {
        // 임의로 코루틴 빌더라 가정. (실제 존재 x)
        // 해당 함수를 통하여, 코루틴으로 작동. -> 함수 실행 도중 나가거나 들어올 수 있다.
        // suspend 로 선언된 메서드를 만나기 전 까지는 일반적으로 실행되다가, 해당 메서드를 만나게 될 때,
        // 해당 메서드에 대한 실행을 다른 쓰레드 혹은 동시성 프로그래밍으로 실행하도록 넘겨주며,
        // drawPerson()을 잠시 탈출하여 메인쓰레드가 다른 코드를 실행한 후, drawBody() 부터 resume 된다.
        // 즉, 함수를 중간에 빠져나와 다른함수에 진입하고, 다시 원점으로 돌아와 멈췄던 부분부터 시작하는 특성은
        // 동시성 프로그래밍을 가능하게 한다.
        // 동시에 여러 업무를 처리하는 병렬성 프로그래밍과는 다르다.
        // 다중 쓰레드를 통하여 동시성을 지원하면, 컨텍스트 스위칭 (프로세스가 CPU 를 사용중인 상태에서 다른 프로세스가 CPU 를 사용하도록 하기 위하여,
        // 이전 프로세스의 상태를 보관하고 새로운 프로세스의 상태를 적재하는 작업) 이 발생하여 자원이 많이 든다.
        // 그러므로 하나의 쓰레드로 동시성 작업을 하는 코루틴은 다중 쓰레드보다 가볍다.

        startCoroutine {
            drawHead()
            drawBody()
            drawLegs()
        }
    }

    suspend fun drawHead(){
        delay(2000)
        //...
    }

    suspend fun drawBody(){
        delay(5000)
        //...
    }

    suspend fun drawLegs(){
        delay(3000)
        //...
    }*/