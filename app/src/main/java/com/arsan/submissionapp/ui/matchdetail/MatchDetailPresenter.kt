package com.arsan.submissionapp.ui.matchdetail

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.arsan.submissionapp.data.network.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getMatchDetail(idEvent: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getMatchDetail(idEvent)),
                    MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events)
            }
        }
    }

    fun getHomeTeam(idTeam: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getTeamDetail(idTeam)),
                    TeamResponse::class.java)
            uiThread {
                view.showHomeTeam(data.teams)
            }
        }
    }

    fun getAwayTeam(idTeam: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getTeamDetail(idTeam)),
                    TeamResponse::class.java)
            uiThread {
                view.showAwayTeam(data.teams)
            }
        }
    }

}