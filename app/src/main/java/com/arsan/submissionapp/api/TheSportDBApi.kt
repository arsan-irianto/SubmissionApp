package com.arsan.submissionapp.api

import com.arsan.submissionapp.BuildConfig

object TheSportDBApi {
    fun getPastMatch(league : String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" + league
    }
}