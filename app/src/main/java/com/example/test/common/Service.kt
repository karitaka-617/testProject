package com.example.test.common

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.*


interface Service {
    //一覧
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Data>>

    //詳細
    @GET("repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Data>
}