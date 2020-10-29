package com.example.newsapp.ui.fragments.everything

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.extension.PaginationListener
import com.example.newsapp.extension.showToast
import com.example.newsapp.model.Articles
import com.example.newsapp.model.Source
import com.example.newsapp.network.Resource
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_everything.*
import org.koin.android.ext.android.inject

class EverythingFragment : BaseFragment<EverythingViewModel>(R.layout.fragment_everything),
    NewsAdapter.Likeclick {

    override val viewModel by inject<EverythingViewModel>()
    private var adapterEverything: NewsAdapter? = null
    private var listEverythingFragment: MutableList<Articles> = mutableListOf()
    private val linearManager = LinearLayoutManager(activity?.parent)

    override fun initial() {
        search()
        setupScroll()
        getData()
        recyclerView()
        detailStart()
        swipeListener()
        viewModel.fetchEverything("bitcoin")
    }

    private fun getData() {
//        viewModel.getDataBase.observe(viewLifecycleOwner, Observer {
//            adapterEverything?.update(it as MutableList<Articles>)
//        })
        listEverythingFragment.add(
            Articles(
                Source("1", "kd"),
                "Lermontov", "32ewe", "eijfieja",
                "", "", "", null, false, 1
            )
        )
        listEverythingFragment.add(
            Articles(
                Source("3", "kd"),
                "Mihailov", "fds313a", "eijfieja",
                "", "", "", null, false, 2
            )
        )
        listEverythingFragment.add(
            Articles(
                Source("2", "k2321d"),
                "Maksimov", "jfdaskjflkasdjfjals", "eijfieja",
                "", "", "", null, false, 3
            )
        )
        adapterEverything?.update(listEverythingFragment)
    }

    private fun recyclerView() {
        adapterEverything = NewsAdapter(listEverythingFragment)
        everyRWFr.apply {
            layoutManager = LinearLayoutManager(activity?.parent)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = adapterEverything
        }
    }

    private fun swipeListener() {
        swipe.setOnRefreshListener {
            listEverythingFragment.clear()
            viewModel.fetchEverything("bitcoin")
            context?.let {
                showToast(it, "Data update")
            }
            swipe.isRefreshing = false
        }
    }

    override fun observe() {

    }

    private fun subscribeEverything() {
        viewModel.fetchEverything("bitcoin").observe(viewLifecycleOwner, Observer {
            val articles = it.data?.articles
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (articles != null) {
                        adapterEverything?.update(articles)
                    }
                }
            }
        })
    }

    private fun setupScroll() {
        everyRWFr?.addOnScrollListener(object : PaginationListener(linearManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                subscribeEverything()
            }
        })
    }

    private fun search() {
        mainSearch.doAfterTextChanged {
            if (it != null) {
                if (mainSearch.text.isNotEmpty()) {
                    listEverythingFragment.clear()
                    viewModel.fetchEverything(mainSearch.text.toString())
                    subscribeEverything()
                    adapterEverything?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun detailStart() {
        adapterEverything?.setOnClick(object : NewsAdapter.OnItemClickListener {
            override fun onClickListener(article: Articles) {
                DetailNewsActivity.instanceActivity(activity, article)
            }
        })
        adapterEverything?.setOnFavClick(object : NewsAdapter.Likeclick {
            override fun onLikeClick(article: Articles) {
                viewModel.insertFavorite(article)
                //                val bundle = Bundle()
                //                bundle.putSerializable("article_key", article) // Put anything what you want
                //
                //                val fragment2 = FavoritesFragment()
                //                fragment2.arguments = bundle
            }
        })
    }

    override fun onLikeClick(article: Articles) {
        viewModel.insertFavorite(article)
    }

}