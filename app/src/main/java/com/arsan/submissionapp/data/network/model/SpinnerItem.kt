package com.arsan.submissionapp.data.network.model

data class SpinnerItem(var leagueCode: String?, var leagueName: String?) {
    override fun toString(): String {
        return leagueName.toString()
    }
}