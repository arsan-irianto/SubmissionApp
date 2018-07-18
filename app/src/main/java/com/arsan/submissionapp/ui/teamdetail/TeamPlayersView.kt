package com.arsan.submissionapp.ui.teamdetail

import com.arsan.submissionapp.data.network.model.Player

interface TeamPlayersView {
    fun showLoading()
    fun hideLoading()
    fun showOverview(data: List<Player>)
}