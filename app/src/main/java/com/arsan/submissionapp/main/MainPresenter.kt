package com.arsan.submissionapp.main

import com.arsan.submissionapp.api.ApiRepository
import com.arsan.submissionapp.api.TheSportDBApi
import com.arsan.submissionapp.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getPastMatch(idLeague : String?){
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getPastMatch(idLeague)),
                    MatchResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

}