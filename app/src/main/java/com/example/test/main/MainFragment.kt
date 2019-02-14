package com.example.test.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.common.Test
import com.example.test.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText


class MainFragment : Fragment() {

    private var binding: MainFragmentBinding? = null
    @NonNull
    private var mainViewModel: MainViewModel? = null

    private var mActivity: MainActivity? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    //ViewModelをBindingする
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding!!.setLifecycleOwner(this)
        binding!!.data = mainViewModel

        //return inflater.inflate(R.layout.main_fragment, container, false)
        return binding!!.root
    }

    //ViewModelの値の変化を検知(Observer)、Listenerのセッティング
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity as MainActivity
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //初期値の設定
        mainViewModel?.setData()
        binding!!.data = mainViewModel

        addTextChanged(editText)
        onStartSecond(buttonNext)

        mainViewModel!!.getData().observe(this,Observer<Test> {})
        mainViewModel!!.getText().observe(this,Observer<String> {})
        mainViewModel!!.getCheckable().observe(this,Observer<Boolean> {})
    }

    private fun addTextChanged(editText: EditText){
        editText.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(t: Editable?) {
//                Log.d("mvvmTest",t.toString())
                mainViewModel!!.checkText(t.toString())
                mainViewModel!!.setEditText(t.toString())
            }
        })
    }

    private fun onStartSecond(button:Button){
        button.setOnClickListener {
            mActivity!!.onStartSecond()
        }
    }
}