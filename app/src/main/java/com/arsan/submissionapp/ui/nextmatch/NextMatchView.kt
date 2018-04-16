package com.arsan.submissionapp.ui.nextmatch

import com.arsan.submissionapp.data.network.model.Match

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}