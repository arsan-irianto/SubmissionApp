package com.arsan.submissionapp.ui.nextmatch

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getNextMatch(idLeague: String?) {
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getNextMatch(idLeague)),
                        MatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

}