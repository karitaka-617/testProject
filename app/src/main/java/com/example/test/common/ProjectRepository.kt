package com.example.test.common

import android.arch.lifecycle.LiveData
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.arch.lifecycle.MutableLiveData
import android.R.attr.data
import android.databinding.adapters.NumberPickerBindingAdapter.setValue
import android.support.annotation.Nullable
import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import retrofit2.Call


object ProjectRepository {
    //Retrofitインターフェース(APIリクエストを管理)
    var HTTPS_API_GITHUB_URL = "https://api.github.com/"

    private var projectRepository: ProjectRepository? = null

    // Github取得API
    private fun gitClient(): Service {
        return getRetrofitService(HTTPS_API_GITHUB_URL)
    }

    private fun getRetrofitService(baseUrl: String) : Service {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Service::class.java)
    }

    @Synchronized
    fun getInstance(): ProjectRepository {
        if (projectRepository == null) {
            projectRepository = ProjectRepository
        }
        return projectRepository as ProjectRepository
    }

    //ユーザーを選択してのリスト取得
    fun getProjectList(user: String): MutableLiveData<List<Data>> {
        val data = MutableLiveData<List<Data>>()

        //Retrofitで非同期リクエスト->Callbackで(自分で実装したModel)型ListのMutableLiveDataにセット
        gitClient().getProjectList(user).enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, @Nullable response: Response<List<Data>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                //TODO: null代入良くない + エラー処理
                data.value = null
            }
        })

        return data
    }

    //ユーザーを選択してのリスト取得(async)
    fun getProjectListAsync(user: String): Deferred<MutableLiveData<List<Data>>> = async {
        val data = MutableLiveData<List<Data>>()

        //Retrofitで非同期リクエスト->Callbackで(自分で実装したModel)型ListのMutableLiveDataにセット
        gitClient().getProjectList(user).enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, @Nullable response: Response<List<Data>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                //TODO: null代入良くない + エラー処理
                data.value = null
            }
        })

        return@async data
    }

    fun getProjectDetails(userID: String, projectName: String): LiveData<Data> {
        val data = MutableLiveData<Data>()

        gitClient().getProjectDetails(userID, projectName).enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                //simulateDelay()
                data.value = response.body()
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                //TODO: null代入良くない + エラー処理
                data.value = null
            }
        })
        return data
    }

    fun getTestData(): Test{
        //val test = Test()
        val testData = Test("test",1.toString())

        //test.value = testData
        return testData
    }

    fun getListData(): ArrayList<String>{
        var array = ArrayList<String>()
        array.add("banana")
        array.add("apple")
        array.add("grape")
        array.add("orange")

        return array
    }
}