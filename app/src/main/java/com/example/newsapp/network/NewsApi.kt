package com.example.newsapp.network

import com.example.newsapp.model.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    fun fetchEverything(@Query("q") q: String?, @Query("apiKey") apiKey: String, @Query("pageSize") size: Int): Call<ResponseBody>

//    @GET("v2/everything?apiKey${Constants.apiKey}")
//    fun fetchSources(@Query("q") q: String)
//
//    @GET("v2/everything?apiKey${Constants.apiKey}")
//    fun fetch(@Query("q") q: String)

}