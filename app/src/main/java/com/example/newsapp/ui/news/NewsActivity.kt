package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.extension.showToast
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity(), NewsAdapter.Listener {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setupViews()
        subscriptions()
    }

    private fun setupViews() {
        setupRecyclerView()
        nestedScrollListener()
    }

    private fun subscriptions() {
        subscribeToTopHeadlines()
    }

    private fun subscribeToEverything() {
        viewModel.fetchEverything("bitcoin").observe(this, Observer {
            when (it.isNullOrEmpty()) {
                false -> updateAdapter(it)
                true -> setLastPage()
            }
        })
    }

    private fun subscribeToTopHeadlines() {
        viewModel.fetchTopHeadlines().observe(this, Observer {
            when (it.isNullOrEmpty()) {
                false -> updateAdapter(it)
                true -> setLastPage()
            }
        })
    }

    private fun setLastPage() {
        viewModel.isLastPage = true
        showToast(R.string.no_more_info)
    }

    private fun nestedScrollListener() {
        nestedScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    if (!viewModel.isLastPage) subscribeToEverything()
                }
            })
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun updateAdapter(list: MutableList<Articles>?) {
        list?.let { adapter.update(it) }
    }

    override fun onItemClick(item: Articles) {
        DetailNewsActivity.instanceActivity(this, item)
    }

}