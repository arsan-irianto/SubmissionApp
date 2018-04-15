package com.arsan.submissionapp.data.network

import com.arsan.submissionapp.BuildConfig

object TheSportDBApi {
    fun getPrevMatch(league : String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" + league
    }

    fun getNextMatch(league: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=" + league
    }
}