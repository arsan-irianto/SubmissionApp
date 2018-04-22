package com.arsan.submissionapp.ui.matchdetail

import com.arsan.submissionapp.TestContextProvider
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.arsan.submissionapp.data.network.model.Team
import com.arsan.submissionapp.data.network.model.TeamResponse
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var matchDetailPresenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchDetailPresenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetail() {
        val matchDetail : MutableList<Match> = mutableListOf()
        val response = MatchResponse(matchDetail)
        val eventid = "526862"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(eventid)),
                MatchResponse::class.java)).thenReturn(response)

        matchDetailPresenter.getMatchDetail(eventid)

        verify(view).showLoading()
        verify(view).showMatchDetail(matchDetail)
        verify(view).hideLoading()
    }

    @Test
    fun getHomeTeam() {
        val teamDetail : MutableList<Team> = mutableListOf()
        val response = TeamResponse(teamDetail)
        val teamid = "133619"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(teamid)),
                TeamResponse::class.java)).thenReturn(response)

        matchDetailPresenter.getHomeTeam(teamid)

        verify(view).showHomeTeam(teamDetail)
    }

    @Test
    fun getAwayTeam() {
        val teamDetail : MutableList<Team> = mutableListOf()
        val response = TeamResponse(teamDetail)
        val teamid = "133932"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(teamid)),
                TeamResponse::class.java)).thenReturn(response)

        matchDetailPresenter.getAwayTeam(teamid)

        verify(view).showAwayTeam(teamDetail)
    }
}