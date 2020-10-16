package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.extension.showToast
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var list: MutableList<Articles>
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter
    private var flag: Boolean? = true
    private var isRequest: Boolean? = true
    private var page = 0
    private var pageItems = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setupRecyclerView()
        getDatabase()
        viewModel.fetchTopHeadlines(page)
        subscribeToNews()
        search()
        nestedScrollListener()
    }

    private fun nestedScrollListener() {
        nestedScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    if (viewModel.articles.value!!.size >= 10) {
                        page++
                        progressbar.visibility = View.VISIBLE
                        if (flag!!) {
                            viewModel.fetchEverything("kotlin")
                            progressbar.visibility = View.GONE
                        } else {
                            viewModel.fetchTopHeadlines(page)
                            progressbar.visibility = View.GONE
                        }
                        subscribeToNews()
                    }
                }
            })
    }

    private fun search() {
        editQuery.doAfterTextChanged {
            if (it != null) {
                if (editQuery.text.isNotEmpty()) {
                    list.clear()
                    viewModel.fetchEverything(editQuery.text.toString())
                    subscribeToNews()
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            adapter = this@NewsActivity.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        list = mutableListOf()
        adapter = NewsAdapter(list, this)
    }

    private fun subscribeToNews() {
        viewModel.articles.observe(this, Observer {
            adapter.update(it)
            insertDatabase(it)
            progressbar.visibility = View.VISIBLE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.item_change_news) {
            flag = if (flag!!) {
                showToast(this, "Everything")
                editQuery.visibility = View.VISIBLE
                list.clear()
                viewModel.articles.observe(this, Observer {
                    insertDatabase(it)
                })
                viewModel.fetchEverything("kotlin")
                subscribeToNews()
                item.setIcon(R.drawable.ic_ever_news)
                page = 0
                isRequest = true
                false
            } else {
                showToast(this, "Top")
                editQuery.visibility = View.GONE
                list.clear()
                viewModel.fetchTopHeadlines(page)
                item.setIcon(R.drawable.ic_top_news)
                subscribeToNews()
                isRequest = false
                page = 0
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDatabase(article: MutableList<Articles>) {
        viewModel.insertNews(article)
        Log.e("TAG", "insertDatabase: $article")
    }

    private fun getDatabase() {
        adapter.update(viewModel.getNews())
        Log.e("TAG", "getDatabase: ${viewModel.getNews()}")
    }

    override fun onClickListener(article: Articles) {
        DetailNewsActivity.instanceActivity(this, article)
    }

}