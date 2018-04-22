package com.arsan.submissionapp.ui.nextmatch

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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private lateinit var view: NextMatchView

    @Mock private lateinit var apiRepository: ApiRepository

    @Mock private lateinit var gson: Gson

    @Mock private lateinit var nextMatchPresenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        nextMatchPresenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getNextMatch() {
        val nextMatch : MutableList<Match> = mutableListOf()
        val response = MatchResponse(nextMatch)
        val leagueid = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(leagueid)),
                MatchResponse::class.java)).thenReturn(response)

        nextMatchPresenter.getNextMatch(leagueid)

        verify(view).showLoading()
        verify(view).showMatchList(nextMatch)
        verify(view).hideLoading()
    }
}