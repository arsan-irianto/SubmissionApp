package com.arsan.submissionapp.ui.teamdetail

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.util.CoroutineContextProvider
import com.google.gson.Gson
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.arsan.submissionapp.data.network.model.TeamResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamOverviewPresenter(private val view: TeamOverviewView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamOverView(idTeam : String?){
        view.showLoading()
        async(context.main) {
            val data = bg{ gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getTeamDetail(idTeam)),
                    TeamResponse::class.java)
            }
            view.showOverview(data.await().teams)
            view.hideLoading()
        }
    }
}