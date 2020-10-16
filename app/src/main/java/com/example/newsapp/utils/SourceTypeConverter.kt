package com.example.newsapp.utils

import androidx.room.TypeConverter
import com.example.newsapp.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class SourceTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun toSource(source: Source): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun fromSource(data: String): Source {
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(data, type)
    }
}