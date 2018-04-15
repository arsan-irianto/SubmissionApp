package com.arsan.submissionapp.ui.nextmatch

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getNextMatch(idLeague : String?){
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getNextMatch(idLeague)),
                    MatchResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

}