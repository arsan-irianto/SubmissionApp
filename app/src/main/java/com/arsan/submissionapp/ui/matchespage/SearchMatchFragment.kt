package com.arsan.submissionapp.ui.matchespage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.ui.matchdetail.MatchDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search_match.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class SearchMatchFragment : Fragment(), SearchMatchView, SearchView.OnQueryTextListener {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MatchSearchAdapter
    private lateinit var progressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipe_refresh_sm.isRefreshing = false
        progressBar = this.progress_bar_sm

        adapter = MatchSearchAdapter(requireContext(), matches){
            startActivity<MatchDetailActivity>("id" to "${it.idEvent}",
                    "homeTeam" to "${it.idHomeTeam}",
                    "awayTeam" to "${it.idAwayTeam}")
        }

        this.rv_sm.adapter = adapter
        this.rv_sm.layoutManager = LinearLayoutManager(requireContext())

        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MatchSearchPresenter(this, apiRequest, gson)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_search_match, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.searchmenu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search..."
        searchItem.expandActionView()


        searchView.setOnQueryTextListener(this)
        searchItem.actionView = searchView
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearchMatch(data: List<Match>) {
        swipe_refresh_sm.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText.length > 3) {
            presenter.getSearchMatchResult(newText)
            adapter.notifyDataSetChanged()
        }
        return true
    }


}
