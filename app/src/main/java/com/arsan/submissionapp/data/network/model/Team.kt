package com.arsan.submissionapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        var teamID: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var formedYear: String? = null,

        @SerializedName("strStadium")
        var stadium: String? = null,

        @SerializedName("strDescriptionEN")
        var overview: String? = null
)