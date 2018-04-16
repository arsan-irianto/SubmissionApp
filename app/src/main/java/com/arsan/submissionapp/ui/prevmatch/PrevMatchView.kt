package com.arsan.submissionapp.ui.prevmatch

import com.arsan.submissionapp.data.network.model.Match

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}