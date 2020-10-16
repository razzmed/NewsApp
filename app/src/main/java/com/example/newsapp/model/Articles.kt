package com.example.newsapp.model

import androidx.annotation.NonNull
import androidx.room.*
import com.example.newsapp.utils.SourceTypeConverter
import com.google.gson.annotations.SerializedName

data class Source(
    var id: String?,
    var name: String?
)

@Entity(tableName = "articles")
@TypeConverters(SourceTypeConverter::class)
data class Articles(
    @SerializedName("source")
    var source: Source?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("content")
    var content: String?,
    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int?

)