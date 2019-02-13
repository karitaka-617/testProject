package com.example.test.second

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.test.common.Data
import com.example.test.common.ProjectRepository.getListData
import com.example.test.common.ProjectRepository.getProjectList
import kotlin.concurrent.thread

class FragmentSecondViewModel : ViewModel() {
    private var listData: MutableLiveData<MutableList<String>> = MutableLiveData()
    private var text: MutableLiveData<String> = MutableLiveData()
    private var clickable: MutableLiveData<Boolean> = MutableLiveData()
    private var gitListData: MutableLiveData<List<Data>> = MutableLiveData()
    private var oldListData: MutableList<String> = mutableListOf()

    init{
        clickable.value = false
    }

    fun setGitListData(user: String){
        gitListData = getProjectList(user)
    }

    fun getGitListData(): LiveData<List<Data>>{
        return gitListData
    }

    fun setListsDataInit(){
        listData.value = getListData()
    }

    fun getListsData(): MutableLiveData<MutableList<String>>{
//        listData.postValue(listData.value)
        return listData
    }

    fun getOldListData(): MutableList<String>{
        return oldListData
    }

    fun addListData(text: String){
        oldListData = listData.value!!.toMutableList()
        listData.value!!.add(text)
        listData.value = listData.value
    }

    fun deleteListData(index: Int){
        oldListData = listData.value!!.toMutableList()
        listData.value!!.removeAt(index)
        listData.value = listData.value
    }

    fun setEditText(editText: String){
        text.value = editText
    }

    fun getText(): LiveData<String> {
        return text
    }

    fun resetText(){
        text.value = ""
    }

    fun checkText(editText: String){
        clickable.value = editText.isNotEmpty()
    }

    fun getClickable(): LiveData<Boolean> {
        return clickable
    }
}
