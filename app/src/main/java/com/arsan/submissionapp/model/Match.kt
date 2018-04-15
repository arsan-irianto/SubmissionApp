package com.arsan.submissionapp.model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("strHomeTeam")
    var homeTeam : String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam : String? = null,

    @SerializedName("intHomeScore")
    var homeScore : String?= null,

    @SerializedName("intAwayScore")
    var awayScore : String?= null,

    @SerializedName("dateEvent")
    var dateEvent : String? = null
)