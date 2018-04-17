package com.arsan.submissionapp.ui.matchdetail


import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.data.network.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Match>)
    fun showHomeTeam(data: List<Team>)
    fun showAwayTeam(data: List<Team>)
}