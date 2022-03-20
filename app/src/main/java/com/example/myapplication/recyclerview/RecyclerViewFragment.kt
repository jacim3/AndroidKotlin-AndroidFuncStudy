package com.example.myapplication.recyclerview

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.RecyclerviewFragmentBinding

class RecyclerViewFragment : Fragment() {

    private lateinit var viewModel: RecyclerViewViewModel
    private lateinit var binding: RecyclerviewFragmentBinding
    private lateinit var dialog: AlertDialog
    private var listCursor = 1
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RecyclerViewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecyclerviewFragmentBinding.inflate(layoutInflater, container, false)
        binding.recyclerViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedViewModel: com.example.myapplication.MainViewModel by activityViewModels()
        val orderMap = HashMap<String, String>()

        setDialog()
        orderMap["_order"] = "desc"
        orderMap["_sort"] = "id"

        viewModel.searchData(listCursor, orderMap, dialog)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listData.observe(viewLifecycleOwner, Observer { it ->

            if (it.isSuccessful) {

                RecyclerViewAdapter.getListData(it.body()!!)

                if (binding.recyclerView.adapter == null){
                    binding.recyclerView.adapter = RecyclerViewAdapter
                } else
                    RecyclerViewAdapter.refreshData()

                Handler(Looper.getMainLooper()).postDelayed({
                    dialog.dismiss()

                }, 1000)
            }
        })

        binding.buttonNext.setOnClickListener {
            listCursor++
            viewModel.searchData(listCursor, orderMap, dialog)
        }

        binding.buttonPrev.setOnClickListener {
            listCursor--
            if (listCursor <=0) {
                listCursor = 1

            } else
                viewModel.searchData(listCursor, orderMap, dialog)
        }
    }

    private fun setDialog() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("로딩중")
/*      builder.setPositiveButton("YES") { dialogInterface, i ->
            {
                //TODO
            }
        }.setNegativeButton("No") { dialogInterface, i ->
            {
                //TODO
            }
        }
*/
        dialog = builder.create()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    companion object {
        fun newInstance() = RecyclerViewFragment()
    }

}