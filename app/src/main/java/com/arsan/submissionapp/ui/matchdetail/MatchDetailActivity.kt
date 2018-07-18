package com.arsan.submissionapp.ui.matchdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.arsan.submissionapp.R
import com.arsan.submissionapp.R.drawable.ic_add_to_favorites
import com.arsan.submissionapp.R.drawable.ic_added_to_favorites
import com.arsan.submissionapp.R.id.*
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.data.network.model.Team
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.arsan.submissionapp.R.menu.match_detail_menu
import com.arsan.submissionapp.data.db.database
import com.arsan.submissionapp.data.db.model.FavoritesMatch
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var id: String
    private lateinit var homeTeam: String
    private lateinit var awayTeam: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        progressBar = this.progress_bar

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        id = intent.getStringExtra("id")
        homeTeam = intent.getStringExtra("homeTeam")
        awayTeam = intent.getStringExtra("awayTeam")

        val apiRequest = ApiRepository()
        val gson = Gson()
        matchDetailPresenter = MatchDetailPresenter(this, apiRequest, gson)
        matchDetailPresenter.getMatchDetail(id)
        matchDetailPresenter.getHomeTeam(homeTeam)
        matchDetailPresenter.getAwayTeam(awayTeam)

        favoriteState()

    }

    override fun showLoading() {
        progressBar.visible()
    }


    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Match>) {

        match = Match(data[0].idEvent,
                data[0].dateEvent,
                data[0].idHomeTeam,
                data[0].homeTeam,
                data[0].homeScore,
                data[0].idAwayTeam,
                data[0].awayTeam,
                data[0].awayScore
        )

        event_date.text = data[0].dateEvent
        home_score.text = data[0].homeScore
        away_score.text = data[0].awayScore
        home_team.text = data[0].homeTeam
        away_team.text = data[0].awayTeam
        home_goals.text = data[0].homeGoalDetails
        away_goals.text = data[0].awayGoalDetails
        home_shots.text = data[0].homeShots
        away_shots.text = data[0].awayShots

        home_goalkeeper.text = data[0].homeLineupGoalkeeper
        away_goalkeeper.text = data[0].awayLineupGoalkeeper

        home_defense.text = data[0].homeLineupDefense
        away_defense.text = data[0].awayLineupDefense

        home_midfield.text = data[0].homeLineupMidfield
        away_midfield.text = data[0].awayLineupMidfield

        home_forward.text = data[0].homeLineupForward
        away_forward.text = data[0].awayLineupForward

        home_substitutes.text = data[0].homeLineupSubstitutes
        away_substitutes.text = data[0].awayLineupSubstitutes
    }

    override fun showHomeTeam(data: List<Team>) {
        Glide.with(this).load(data[0].teamBadge).into(home_badge)
    }

    override fun showAwayTeam(data: List<Team>) {
        Glide.with(this).load(data[0].teamBadge).into(away_badge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(match_detail_menu, menu)
        menuItem = menu
        setFavorites()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorites() else addToFavorite()

                isFavorite = !isFavorite
                setFavorites()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoritesMatch.TABLE_FAVORITE,
                        FavoritesMatch.EVENT_ID to match.idEvent,
                        FavoritesMatch.EVENT_DATE to match.dateEvent,
                        FavoritesMatch.HOME_TEAM_ID to match.idHomeTeam,
                        FavoritesMatch.HOME_TEAM to match.homeTeam,
                        FavoritesMatch.HOME_SCORE to match.homeScore,
                        FavoritesMatch.AWAY_TEAM_ID to match.idAwayTeam,
                        FavoritesMatch.AWAY_TEAM to match.awayTeam,
                        FavoritesMatch.AWAY_SCORE to match.awayScore)
            }
            snackbar(swipe_refresh, "Added to favorites").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipe_refresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorites() {
        try {
            database.use {
                delete(FavoritesMatch.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to id)
            }
            snackbar(swipe_refresh, "Remove From Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipe_refresh, e.localizedMessage).show()
        }
    }

    private fun setFavorites() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoritesMatch.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoritesMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
