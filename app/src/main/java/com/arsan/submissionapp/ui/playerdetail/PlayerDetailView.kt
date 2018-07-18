package com.arsan.submissionapp.ui.playerdetail

import com.arsan.submissionapp.data.network.model.Player

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showProfile(data: List<Player>)
}