package com.arsan.submissionapp.ui.teamspage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.*

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.SpinnerItem
import com.arsan.submissionapp.data.network.model.Team
import com.arsan.submissionapp.ui.teamdetail.TeamDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams_page.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 *
 */
class TeamsPageFragment : Fragment(), TeamsPageView {

    private var teams: MutableList<Team> = mutableListOf()
    private var spinnerItems: MutableList<SpinnerItem> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: TeamsPagePresenter
    private lateinit var adapter: TeamsPageAdapter
    private lateinit var spinner: Spinner


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipe_refresh_teams.isRefreshing = false
        progressBar = this.progress_bar_teams

        adapter = TeamsPageAdapter(requireContext(), teams) {
            startActivity<TeamDetailActivity>("id" to "${it.teamID}",
                    "teamName" to "${it.teamName}",
                    "teamBadge" to "${it.teamBadge}",
                    "formedYear" to "${it.formedYear}",
                    "stadium" to "${it.stadium}")
        }

        this.rv_teams.adapter = adapter
        this.rv_teams.layoutManager = LinearLayoutManager(requireContext())

        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = TeamsPagePresenter(this, apiRequest, gson)
        presenter.getTeamList("4328")

        spinner = this.spinner_league

        spinnerItems.add(SpinnerItem("4328", "English Premier League"))
        spinnerItems.add(SpinnerItem("4331", "German Bundesliga"))
        spinnerItems.add(SpinnerItem("4332", "Italian Serie A"))
        spinnerItems.add(SpinnerItem("4334", "French Ligue 1"))
        spinnerItems.add(SpinnerItem("4335", "Spanish La Liga"))
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val item : SpinnerItem = parent?.selectedItem as SpinnerItem
                val idleague = spinnerItems[position].leagueCode
                presenter.getTeamList(idleague.toString())
            }

        }

        swipe_refresh_teams.onRefresh {
            presenter.getTeamList("4328")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_page, container, false)
    }

    companion object {
        fun newInstance(): TeamsPageFragment = TeamsPageFragment()
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.searchmenu, menu)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val teamSearchFragment = TeamSearchFragment()
        openFragment(teamSearchFragment)
        return super.onOptionsItemSelected(item)
    }

}
