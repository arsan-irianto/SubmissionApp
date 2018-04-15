package com.arsan.submissionapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.arsan.submissionapp.R
import com.arsan.submissionapp.api.ApiRepository
import com.arsan.submissionapp.model.Match
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh

class MainActivity : AppCompatActivity(), MainView {

    private var match : MutableList<Match> = mutableListOf()
    private lateinit var progressBar : ProgressBar
    private lateinit var matchAdapter : MainAdapter
    private lateinit var presenter : MainPresenter
    private val leagueId : String? = "4328"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe_refresh.isRefreshing = false
        progressBar = this.progress_bar


        matchAdapter = MainAdapter(this, match)
        rv_schedule.adapter = matchAdapter
        rv_schedule.layoutManager = LinearLayoutManager(this)

        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, apiRequest, gson)
        presenter.getPastMatch(leagueId)

        swipe_refresh.onRefresh {
            presenter.getPastMatch(leagueId)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipe_refresh.isRefreshing = false
        match.clear()
        match.addAll(data)
        matchAdapter.notifyDataSetChanged()
    }

}
