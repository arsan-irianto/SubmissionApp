package com.arsan.submissionapp.ui.teamspage

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.TeamResponse
import com.arsan.submissionapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async

class TeamsPagePresenter(private val view: TeamsPageView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                        TeamResponse::class.java)
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }

    }

    fun getQueryTeams(filterText : String?){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchTeams(filterText)),
                        TeamResponse::class.java)
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}