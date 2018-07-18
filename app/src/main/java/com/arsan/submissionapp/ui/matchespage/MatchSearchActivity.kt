package com.arsan.submissionapp.ui.matchespage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.ui.matchdetail.MatchDetailActivity
import com.arsan.submissionapp.ui.nextmatch.NextMatchAdapter
import com.arsan.submissionapp.ui.nextmatch.NextMatchPresenter
import com.arsan.submissionapp.ui.nextmatch.NextMatchView
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_search.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast
import java.util.ArrayList

class MatchSearchActivity : AppCompatActivity(), NextMatchView, SearchView.OnQueryTextListener {

    private var nextMatch: MutableList<Match> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var nextMatchAdapter: NextMatchAdapter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private val leagueId: String? = "4328"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        swipe_refresh_match.isRefreshing = false
        progressBar = this.progress_bar_match


        nextMatchAdapter = NextMatchAdapter(this, nextMatch) {
            startActivity<MatchDetailActivity>("id" to "${it.idEvent}",
                    "homeTeam" to "${it.idHomeTeam}",
                    "awayTeam" to "${it.idAwayTeam}")
        }
        this.rv_schedule_match.adapter = nextMatchAdapter
        this.rv_schedule_match.layoutManager = LinearLayoutManager(this)

        val apiRequest = ApiRepository()
        val gson = Gson()
        nextMatchPresenter = NextMatchPresenter(this, apiRequest, gson)
        nextMatchPresenter.getNextMatch(leagueId)

        swipe_refresh_match.onRefresh {
            nextMatchPresenter.getNextMatch(leagueId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.matchsearchview, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search..."
        searchItem.expandActionView()


        searchView.setOnQueryTextListener(this)
        searchItem.actionView = searchView

        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipe_refresh_match.isRefreshing = false
        nextMatch.clear()
        nextMatch.addAll(data)
        nextMatchAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        toast("onQueryTextChange")
        val newText = newText?.toLowerCase()
        val dataFilter = ArrayList<Match>()
        for (data in nextMatch) {
            val nama = data.homeTeam?.toLowerCase()
            if (nama != null) {
                if (nama.contains(newText.toString())) {
                    dataFilter.add(data)
                }
            }
        }
        nextMatch.clear()
        nextMatch.addAll(dataFilter)
        nextMatchAdapter.notifyDataSetChanged()
        return true
    }

}
