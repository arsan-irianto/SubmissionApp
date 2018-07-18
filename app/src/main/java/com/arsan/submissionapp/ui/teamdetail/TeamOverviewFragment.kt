package com.arsan.submissionapp.ui.teamdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Team
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_overview.*
import org.jetbrains.anko.support.v4.intentFor

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamOverviewFragment : Fragment(), TeamOverviewView {
    private lateinit var teamOverviewPresenter: TeamOverviewPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var team: Team

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar = this.progress_bar

        val apiRequest = ApiRepository()
        val gson = Gson()
        val id = arguments?.getString("data")
        //val id = this.activity?.intent?.extras?.getString("idTeam")
        // val id = arguments?.getString("data")?:"no data"
        teamOverviewPresenter = TeamOverviewPresenter(this,apiRequest,gson)
        teamOverviewPresenter.getTeamOverView(id)
        //Toast.makeText(activity, "Adami " + arguments?.getString("data")?:"no data", Toast.LENGTH_LONG).show()
        //Toast.makeText(activity, "TES " + id_t, Toast.LENGTH_LONG).show()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_team_overview, container, false)
    }

    companion object {
        fun newInstance(): TeamOverviewFragment = TeamOverviewFragment()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showOverview(data: List<Team>) {
        team = Team(data[0].overview)
        team_description.text = data[0].overview
    }

}
