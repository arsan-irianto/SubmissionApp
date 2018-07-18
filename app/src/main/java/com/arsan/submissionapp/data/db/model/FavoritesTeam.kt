package com.arsan.submissionapp.data.db.model

data class FavoritesTeam(val id: Long?,
                         val idTeam: String?,
                         val teamName: String?,
                         val teamBadge: String?,
                         val formedYear: String?,
                         val stadium:String?) {

    companion object {
        const val TABLE_FAVTEAM: String = "TABLE_FAVTEAM"
        const val ID: String = "_ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val FORMED_YEAR:String = "FORMED_YEAR"
        const val STADIUM : String = "STADIUM"
    }

}