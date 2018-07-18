package com.arsan.submissionapp.ui.matchespage

import com.arsan.submissionapp.data.network.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatch(data : List<Match>)
}