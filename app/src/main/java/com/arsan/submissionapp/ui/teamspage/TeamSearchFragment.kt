package com.arsan.submissionapp.ui.teamspage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Team
import com.arsan.submissionapp.ui.teamdetail.TeamDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_search.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 *
 */
class TeamSearchFragment : Fragment(), TeamsPageView, SearchView.OnQueryTextListener {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPagePresenter
    private lateinit var adapter: TeamsPageAdapter
    private lateinit var progressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipe_refresh_teams.isRefreshing = false
        progressBar = this.progress_bar_search

        adapter = TeamsPageAdapter(requireContext(), teams) {
            startActivity<TeamDetailActivity>("id" to "${it.teamID}",
                    "teamName" to "${it.teamName}",
                    "teamBadge" to "${it.teamBadge}",
                    "formedYear" to "${it.formedYear}",
                    "stadium" to "${it.stadium}")
        }

        this.rv_team_search.adapter = adapter
        this.rv_team_search.layoutManager = LinearLayoutManager(requireContext())

        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = TeamsPagePresenter(this, apiRequest, gson)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_search, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.searchmenu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search..."
        searchItem.expandActionView()


        searchView.setOnQueryTextListener(this)
        searchItem.actionView = searchView

        //return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText.length > 3) {
            presenter.getQueryTeams(newText)
            adapter.notifyDataSetChanged()
        }
        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipe_refresh_teams.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
