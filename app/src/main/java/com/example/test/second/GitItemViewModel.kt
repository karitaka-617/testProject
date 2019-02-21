package com.example.test.second

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.test.common.*
import kotlinx.coroutines.runBlocking

class GitItemViewModel : ViewModel() {
    private var gitData: MutableLiveData<Data> = MutableLiveData()

    fun setGitData(user: String,projectName: String) = runBlocking{
        val data = ProjectRepository.getGitData(user,projectName)
        gitData.value = data
    }

    fun getGitData(): LiveData<Data>{
        return gitData
    }
}
