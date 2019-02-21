package com.example.test.common

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    suspend fun getGitListData(name:String): List<Data> = GlobalScope.async(Dispatchers.Default){
        val data = gitClient().getProjectList(name)
        val res = data.execute()
        return@async res.body()!!
    }.await()

    suspend fun getGitData(name: String, projectName: String): Data = GlobalScope.async(Dispatchers.Default){
        val data = gitClient().getProjectDetails(name,projectName)
        val res = data.execute()
        return@async res.body()!!
    }.await()

    fun getTestData(): Test{
        //val test = Test()
        val testData = Test("test",1.toString())

        //test.value = testData
        return testData
    }

    fun getListData(): ArrayList<String>{
        val array = ArrayList<String>()
        array.add("banana")
        array.add("apple")
        array.add("grape")
        array.add("orange")

        return array
    }
}