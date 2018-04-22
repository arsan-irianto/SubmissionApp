package com.arsan.submissionapp.ui.prevmatch

import com.arsan.submissionapp.TestContextProvider
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {

    @Mock private lateinit var view: PrevMatchView

    @Mock private lateinit var apiRepository: ApiRepository

    @Mock private lateinit var gson: Gson

    @Mock private lateinit var prevMatchPresenter: PrevMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        prevMatchPresenter = PrevMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPrevMatch() {
        val prevMatch : MutableList<Match> = mutableListOf()
        val response = MatchResponse(prevMatch)
        val leagueid = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPrevMatch(leagueid)),
                MatchResponse::class.java)).thenReturn(response)

        prevMatchPresenter.getPrevMatch(leagueid)

        verify(view).showLoading()
        verify(view).showMatchList(prevMatch)
        verify(view).hideLoading()
    }
}