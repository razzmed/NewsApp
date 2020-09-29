package com.example.newsapp.model

data class ResponseBody(
    var status: String?,
    var totalResults: Int?,
    var articles: MutableList<Articles>,
    var message: String?,
    var code: String?
)