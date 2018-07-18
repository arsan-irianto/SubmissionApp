package com.arsan.submissionapp.ui.nextmatch

import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.TheSportDBApi
import com.arsan.submissionapp.data.network.model.MatchResponse
import com.arsan.submissionapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatch(idLeague: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getNextMatch(idLeague)),
                        MatchResponse::class.java)
            }
            val dataPrev = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getPrevMatch(idLeague)),
                        MatchResponse::class.java)
            }
            if (data.await().events == null) {
                view.showMatchList(dataPrev.await().events)
            } else {
                view.showMatchList(data.await().events)
            }
            view.hideLoading()
        }
    }

}