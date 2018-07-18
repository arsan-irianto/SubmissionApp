package com.arsan.submissionapp.ui.teamdetail

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.PlayerResponse
import com.arsan.submissionapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPlayersPresenter(private val view: TeamPlayersView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamPlayers(team:String?){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getPlayers(team)),
                        PlayerResponse::class.java)
            }

            view.showOverview(data.await().player)
            view.hideLoading()
        }
    }
}