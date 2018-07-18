package com.arsan.submissionapp.ui.favoritespage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.db.database
import com.arsan.submissionapp.data.db.model.FavoritesTeam
import com.arsan.submissionapp.ui.teamdetail.TeamDetailActivity
import com.arsan.submissionapp.util.invisible
import kotlinx.android.synthetic.main.favoritesteam_list.*
import kotlinx.android.synthetic.main.fragment_favorites_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesTeamFragment : Fragment() {

    private var favoritesTeam: MutableList<FavoritesTeam> = mutableListOf()
    private lateinit var favoritesTeamAdapter: FavoritesTeamAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe_refresh_favteam.isRefreshing = false

        favoritesTeamAdapter = FavoritesTeamAdapter(requireContext(), favoritesTeam) {
            startActivity<TeamDetailActivity>("id" to "${it.idTeam}",
                    "teamName" to "${it.teamName}",
                    "teamBadge" to "${it.teamBadge}",
                    "formedYear" to "${it.formedYear}",
                    "stadium" to "${it.stadium}")
        }

        rv_favteam.adapter = favoritesTeamAdapter
        rv_favteam.layoutManager = LinearLayoutManager(requireContext())

        showFavoritesTeam()

        swipe_refresh_favteam.onRefresh {
            favoritesTeam.clear()
            showFavoritesTeam()
        }
    }

    private fun showFavoritesTeam() {
        context?.database?.use {
            swipe_refresh_favteam.isRefreshing = false
            val result = select(FavoritesTeam.TABLE_FAVTEAM)
            val favorite = result.parseList(classParser<FavoritesTeam>())
            favoritesTeam.addAll(favorite)
            favoritesTeamAdapter.notifyDataSetChanged()
            progress_bar_favteam.invisible()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_team, container, false)
    }

    companion object {
        fun newInstance(): FavoritesTeamFragment = FavoritesTeamFragment()
    }
}
