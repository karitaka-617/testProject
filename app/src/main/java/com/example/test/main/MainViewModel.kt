package com.example.test.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var text: MutableLiveData<String> = MutableLiveData()
    private var checkable: MutableLiveData<Boolean> = MutableLiveData()

    init {
        checkable.value = false
    }

    fun setEditText(editText: String){
        text.value = editText
    }

    fun getText(): LiveData<String> {
        return text
    }

    fun checkText(editText: String){
        checkable.value = editText.isNotEmpty()
    }

    fun getCheckable(): LiveData<Boolean> {
        return checkable
    }
}
