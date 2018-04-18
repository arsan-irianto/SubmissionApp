package com.arsan.submissionapp.ui.favorites


import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.db.database
import com.arsan.submissionapp.data.db.model.FavoritesMatch
import com.arsan.submissionapp.ui.matchdetail.MatchDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import kotlinx.android.synthetic.main.fragment_favorites_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesMatchFragment : Fragment() {

    private var favoritesMatch : MutableList<FavoritesMatch> = mutableListOf()
    private lateinit var favoritesMatchAdapter: FavoritesMatchAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe_refresh.isRefreshing = false

        favoritesMatchAdapter = FavoritesMatchAdapter(requireContext(),favoritesMatch){
            startActivity<MatchDetailActivity>("id" to "${it.eventId}",
                    "homeTeam" to "${it.homeTeamId}",
                    "awayTeam" to "${it.awayTeamId}")
        }

        rv_schedule.adapter = favoritesMatchAdapter
        rv_schedule.layoutManager = LinearLayoutManager(requireContext())

        showFavoritesMatch()

        swipe_refresh.onRefresh {
            favoritesMatch.clear()
            showFavoritesMatch()
        }
    }

    private fun showFavoritesMatch() {
        context?.database?.use {
            swipe_refresh.isRefreshing = false
            val result = select(FavoritesMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoritesMatch>())
            favoritesMatch.addAll(favorite)
            favoritesMatchAdapter.notifyDataSetChanged()
            progress_bar.invisible()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_match, container, false)
    }


    companion object {
        fun newInstance(): FavoritesMatchFragment = FavoritesMatchFragment()
    }
}
