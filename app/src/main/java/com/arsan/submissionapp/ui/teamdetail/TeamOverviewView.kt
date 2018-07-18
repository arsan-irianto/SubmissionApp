package com.arsan.submissionapp.ui.teamdetail

import com.arsan.submissionapp.data.network.model.Team

interface TeamOverviewView {
    fun showLoading()
    fun hideLoading()
    fun showOverview(data: List<Team>)
}