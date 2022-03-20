package com.example.myapplication.retrofit

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.RetrofitFragmentBinding

class RetrofitFragment : Fragment() {

    companion object {
        fun newInstance() = RetrofitFragment()
    }

    private lateinit var viewModel: RetrofitViewModel
    private lateinit var binding: RetrofitFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RetrofitViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  RetrofitFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    // Fragment 의 View 들이 생성된 이후 호출.
    // View 의 초기화를 담당하는 로직 작성 부분
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 액티비티 뷰모델 접근
        // ViewModelProvider 사용 시 NullPointerException 발생생
       val sharedViewModel:com.example.myapplication.MainViewModel by activityViewModels()

        Log.e("^&*^&*", sharedViewModel.myResponse.value!!.body()!!.title)

        val options:HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        binding.buttonQuery.setOnClickListener {
            val number = (binding.editTextQuery.text.toString()).toInt()
//            sharedViewModel.getCustomPosts3(number, "id", "desc")
            sharedViewModel.getCustomPosts3(number, options)
            sharedViewModel.myCustomPosts.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    Log.e("^&*", sharedViewModel.myCustomPosts.value.toString())
                    binding.textViewResult.text = it.body().toString()
                    it.body()?.forEach { post ->
                        Log.e("^&*", post.userId.toString())
                        Log.e("^&*", post.title.toString())
                        Log.e("^&*", post.id.toString())
                        Log.e("^&*", post.body.toString())
                    }
                } else {
                    binding.textViewResult.text = it.code().toString()
                }
            })
        }
    }

    // Fragment 에 속하는 View 들의 상태값을 모두 읽어온 후 호출
    // 셋팅된 뷰들을 통하여, 해당 뷰들의 상태값 확인을 통하여 수행하는 로직 작성.
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    // Fragment 의 State 가 저장된 이후에 호출되는 메서드
    // Api 28 이전에서는 이 메서드가 호출되고 onStop() 가 호출.
    // Api 28 이후는 onStop() 가 먼저 호출된 후, 이 메서드 호출
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}