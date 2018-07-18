package com.arsan.submissionapp.ui.playerdetail

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.PlayerDetailResponse
import com.arsan.submissionapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailPresenter(private val view: PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerProfile(playerId: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg{ gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getPlayerDetail(playerId)),
                    PlayerDetailResponse::class.java)
            }
            view.showProfile(data.await().players)
            view.hideLoading()
        }
    }
}