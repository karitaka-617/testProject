package com.example.test.second

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.test.common.Data
import com.example.test.common.ProjectRepository
import com.example.test.common.ProjectRepository.getListData
import kotlinx.coroutines.runBlocking

class FragmentSecondViewModel : ViewModel() {
    private var listData: MutableLiveData<MutableList<String>> = MutableLiveData()
    private var clickable: MutableLiveData<Boolean> = MutableLiveData()
    private var gitListData: MutableLiveData<List<Data>> = MutableLiveData()

    init{
        clickable.value = false
    }

    fun setGitListData(user: String) = runBlocking{
        val data = ProjectRepository.getGitListData(user)
        gitListData.value = data.toMutableList()
    }

    fun getGitListData(): LiveData<List<Data>>{
        return gitListData
    }

    fun setListsDataInit(){
        listData.value = getListData()
    }
}
