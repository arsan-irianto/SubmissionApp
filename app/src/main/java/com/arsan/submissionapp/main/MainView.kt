package com.arsan.submissionapp.main

import com.arsan.submissionapp.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<Match>)
}