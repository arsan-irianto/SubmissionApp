package com.arsan.submissionapp.ui.prevmatch

import android.content.Context
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getPrevMatch(idLeague : String?){
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getPrevMatch(idLeague)),
                    MatchResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

}