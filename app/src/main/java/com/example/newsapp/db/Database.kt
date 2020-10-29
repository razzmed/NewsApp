package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.Articles

//@Database(entities =[Articles::class], version = 2, exportSchema = false)
//abstract class Database : RoomDatabase(){
//    abstract fun articlesDao(): Dao
//}

class Database {
    fun getData(context: Context): NewsData {
        return Room.databaseBuilder(context, NewsData::class.java, "data_name").build()
    }
}