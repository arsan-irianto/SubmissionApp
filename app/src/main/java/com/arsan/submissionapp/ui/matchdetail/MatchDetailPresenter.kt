package com.arsan.submissionapp.ui.matchdetail

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.arsan.submissionapp.data.network.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getMatchDetail(idEvent: String?) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getMatchDetail(idEvent)),
                        MatchResponse::class.java)
            }
            view.showMatchDetail(data.await().events)
            view.hideLoading()
        }
    }

    fun getHomeTeam(idTeam: String?) {
        async(UI) {
            val data = bg{ gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getTeamDetail(idTeam)),
                    TeamResponse::class.java)
            }
            view.showHomeTeam(data.await().teams)
        }
    }

    fun getAwayTeam(idTeam: String?) {
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getTeamDetail(idTeam)),
                        TeamResponse::class.java)
            }
            view.showAwayTeam(data.await().teams)
        }
    }

}