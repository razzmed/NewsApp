package com.example.newsapp.model

data class Source(
    var id: String?,
    var name: String?
)

data class Articles(
    var source: Source?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?

)