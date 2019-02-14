package com.example.test.second

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.test.R
import kotlinx.android.synthetic.main.fragment_second_fragment.*
import android.support.v7.widget.RecyclerView
import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.util.DiffUtil
import com.example.test.second.FragmentSecond.RecyclerFragmentListener
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.example.test.databinding.FragmentSecondFragmentBinding
import kotlinx.android.synthetic.main.fragment_second_fragment.view.*
import kotlin.concurrent.thread


class FragmentSecond : Fragment(), OnRecyclerListener {

    companion object {
        fun newInstance() = FragmentSecond()
    }

    private var binding: FragmentSecondFragmentBinding? = null
    @NonNull
    private var secondViewModel: FragmentSecondViewModel? = null

    private var old:MutableList<String> = mutableListOf()

    // RecyclerViewとAdapter
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerAdapter? = null

    interface RecyclerFragmentListener {
        fun onRecyclerEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondFragmentBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this
        binding!!.viewModel = secondViewModel

        //return inflater.inflate(R.layout.main_fragment, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        secondViewModel = ViewModelProviders.of(this).get(FragmentSecondViewModel::class.java)

        secondViewModel!!.setListsDataInit()
        thread {
            try {
                secondViewModel!!.setGitListData("sample")
                Log.d("mvvmTest",secondViewModel!!.getGitListData().value.toString())
            }catch (e: Exception){
                Log.d("mvvmTest","error:$e")
            }
        }

        binding!!.viewModel = secondViewModel

        //Adapter設定
        recycler_view.layoutManager = LinearLayoutManager(context)
        mAdapter = RecyclerAdapter(context!!, secondViewModel!!.getListsData().value!!, this)
        recycler_view.adapter = mAdapter

        //Listenerの設定
        addTextChanged(editText2)
        addListData(button2)

        //Observer設定
        secondViewModel!!.getText().observe(this,Observer<String> {})
        secondViewModel!!.getClickable().observe(this, Observer<Boolean> {})
        secondViewModel!!.getListsData().observe(this, Observer<MutableList<String>> {
            old = secondViewModel!!.getOldListData()
            val diffResult = DiffUtil.calculateDiff(ListDataDiff(old,it!!))
            diffResult.dispatchUpdatesTo(mAdapter!!)
        })
    }

    override fun onRecyclerClicked(v: View, position: Int) {
        secondViewModel!!.deleteListData(position)
    }

    private fun addTextChanged(editText: EditText){
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(t: Editable?) {
                secondViewModel!!.checkText(t.toString())
                secondViewModel!!.setEditText(t.toString())
            }
        })
    }

    private fun addListData(button: Button){
        button.setOnClickListener {
            val text = editText2.text.toString()
            secondViewModel!!.addListData(text)
            secondViewModel!!.resetText()
            editText2.text.clear()
        }
    }
}
