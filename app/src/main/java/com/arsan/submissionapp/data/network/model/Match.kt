package com.arsan.submissionapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Match(
        @SerializedName("idHomeTeam") var idHomeTeam: String? = null,
        @SerializedName("idAwayTeam") var idAwayTeam: String? = null,
        @SerializedName("strHomeTeam") var homeTeam: String? = null,
        @SerializedName("strAwayTeam") var awayTeam: String? = null,
        @SerializedName("intHomeScore") var homeScore: String? = null,
        @SerializedName("intAwayScore") var awayScore: String? = null,
        @SerializedName("intHomeShots") var homeShots: String? = null,
        @SerializedName("intAwayShots") var awayShots: String? = null,
        @SerializedName("dateEvent") var dateEvent: String? = null,

        @SerializedName("strHomeGoalDetails") var homeGoalDetails: String? = null,
        @SerializedName("strHomeRedCards") var homeRedCards: String? = null,
        @SerializedName("strHomeYellowCards") var homeYellowCards: String? = null,
        @SerializedName("strHomeLineupGoalkeeper") var homeLineupGoalkeeper: String? = null,
        @SerializedName("strHomeLineupDefense") var homeLineupDefense: String? = null,
        @SerializedName("strHomeLineupMidfield") var homeLineupMidfield: String? = null,
        @SerializedName("strHomeLineupForward") var homeLineupForward: String? = null,
        @SerializedName("strHomeLineupSubstitutes") var homeLineupSubstitutes: String? = null,
        @SerializedName("strHomeFormation") var homeFormation: String? = null,

        @SerializedName("strAwayGoalDetails") var awayGoalDetails: String? = null,
        @SerializedName("strAwayRedCards") var awayRedCards: String? = null,
        @SerializedName("strAwayYellowCards") var awayYellowCards: String? = null,
        @SerializedName("strAwayLineupGoalkeeper") var awayLineupGoalkeeper: String? = null,
        @SerializedName("strAwayLineupDefense") var awayLineupDefense: String? = null,
        @SerializedName("strAwayLineupMidfield") var awayLineupMidfield: String? = null,
        @SerializedName("strAwayLineupForward") var awayLineupForward: String? = null,
        @SerializedName("strAwayLineupSubstitutes") var awayLineupSubstitutes: String? = null,
        @SerializedName("strAwayFormation") var awayFormation: String? = null

)
