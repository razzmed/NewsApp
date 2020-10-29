package com.example.newsapp.network

import com.example.newsapp.model.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun fetchEverything(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") size: Int,
        @Query("page") page: Int?
    ): ResponseBody

    @GET("v2/top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    @Query("page") page: Int,
    @Query("items") items: Int
    ): ResponseBody

}