package com.arsan.submissionapp.ui.teamdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Player
import com.arsan.submissionapp.ui.playerdetail.PlayerDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_players.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamPlayersFragment : Fragment(), TeamPlayersView {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var playerAdapter: TeamPlayersAdapter
    private lateinit var playersPresenter: TeamPlayersPresenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressBar = this.progress_bar_player

        playerAdapter = TeamPlayersAdapter(requireContext(), players){
            startActivity<PlayerDetailActivity>("id" to "${it.id}",
                    "player" to "${it.player}",
                    "weight" to "${it.weight}",
                    "height" to "${it.height}",
                    "position" to "${it.position}",
                    "fanArt" to "${it.fanArt}")
        }

        this.rv_players.adapter = playerAdapter
        this.rv_players.layoutManager = LinearLayoutManager(requireContext())

        val teamId = arguments?.getString("data")
        val apiRequest = ApiRepository()
        val gson = Gson()
        playersPresenter = TeamPlayersPresenter(this, apiRequest, gson)
        playersPresenter.getTeamPlayers(teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_players, container, false)
    }

    companion object {
        fun newInstance(): TeamPlayersFragment = TeamPlayersFragment()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showOverview(data: List<Player>) {
        players.clear()
        players.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }

}
