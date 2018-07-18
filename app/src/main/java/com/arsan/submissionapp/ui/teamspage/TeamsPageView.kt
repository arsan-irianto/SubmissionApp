package com.arsan.submissionapp.ui.teamspage

import com.arsan.submissionapp.data.network.model.Team

interface TeamsPageView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}