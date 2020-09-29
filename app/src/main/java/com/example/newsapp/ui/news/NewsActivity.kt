package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.R
import com.example.newsapp.extension.showToast

class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.fetchEverything("bitcoin")
        subscribeToNews()
    }

    private fun subscribeToNews() {
        viewModel.articles.observe(this, Observer {
            showToast(this, it.toString())
        })
    }
}