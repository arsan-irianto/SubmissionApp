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
        val teamHome : MutableList<Team> = mutableListOf()
        val response = TeamResponse(teamHome)
        val teamHomeId = "133619"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamHomeId)),
                TeamResponse::class.java)).thenReturn(response)

        matchDetailPresenter.getHomeTeam(teamHomeId)

        verify(view).showLoading()
        verify(view).showHomeTeam(teamHome)
        verify(view).hideLoading()
    }

    @Test
    fun getAwayTeam() {
        val teamAway : MutableList<Team> = mutableListOf()
        val response = TeamResponse(teamAway)
        val teamAwayId = "133932"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamAwayId)),
                TeamResponse::class.java)).thenReturn(response)

        matchDetailPresenter.getAwayTeam(teamAwayId)

        verify(view).showLoading()
        verify(view).showAwayTeam(teamAway)
        verify(view).hideLoading()
    }
}