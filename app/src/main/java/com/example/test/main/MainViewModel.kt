package com.example.test.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.NonNull
import com.example.test.common.ProjectRepository.getTestData
import com.example.test.common.Test

class MainViewModel : ViewModel() {
    @NonNull
    private var data: MutableLiveData<Test> = MutableLiveData()
    private var text: MutableLiveData<String> = MutableLiveData()
    private var clickable: MutableLiveData<Boolean> = MutableLiveData()
    private val navigator: MainNavigator? = null

    init {
        clickable.value = false
    }

    fun setData(){
        data.value = getTestData()
//        Log.d("mvvmTest",data.value.toString())
//        return data
    }

    fun getData(): LiveData<Test> {
        return data
    }

    fun setEditText(editText: String){
//        Log.d("mvvmTest",editText)
        text.value = editText
    }

    fun getText(): LiveData<String> {
        return text
    }

    fun checkText(editText: String){
        clickable.value = editText.isNotEmpty()
    }

    fun getCheckable(): LiveData<Boolean> {
        return clickable
    }

    fun onClickAddCount(){
        var cnt = data.value!!.id.toInt()
        cnt += 1
        val test = Test(data.value!!.name,cnt.toString())
        data.value = test
//        Log.d("mvvmTest",data.value.toString())
    }
}
