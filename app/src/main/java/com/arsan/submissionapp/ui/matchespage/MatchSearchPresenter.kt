package com.arsan.submissionapp.ui.matchespage

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.EventResponse
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.arsan.submissionapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchSearchPresenter(private val view: SearchMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchMatchResult(searchString : String?){
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getSearchMatch(searchString)),
                        EventResponse::class.java)
            }
            view.showSearchMatch(data.await().event)
            view.hideLoading()
        }
    }
}