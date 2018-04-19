package com.arsan.submissionapp.ui.prevmatch

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getPrevMatch(idLeague: String?) {
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getPrevMatch(idLeague)),
                        MatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

}