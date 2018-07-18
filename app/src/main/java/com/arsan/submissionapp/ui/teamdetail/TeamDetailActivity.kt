package com.arsan.submissionapp.ui.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.arsan.submissionapp.R
import com.arsan.submissionapp.R.id.team_name
import com.arsan.submissionapp.data.db.database
import com.arsan.submissionapp.data.db.model.FavoritesTeam
import com.arsan.submissionapp.data.network.model.Team
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var team: Team
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        val teamName = intent.getStringExtra("teamName")
        val teamBadge = intent.getStringExtra("teamBadge")
        val formedYear = intent.getStringExtra("formedYear")
        val stadium = intent.getStringExtra("stadium")

        supportActionBar?.title = teamName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation= 0F

        team_name.text = teamName
        formed_year.text = formedYear
        stadium_name.text = stadium

        //team = Team()

        team = Team(id,teamName,teamBadge, formedYear, stadium)

        Glide.with(this).load(teamBadge).into(team_badge)

        val bundle = Bundle()
        bundle.putString("data", id)
        var teamDetailFragment = TeamDetailFragment()
        teamDetailFragment.arguments = bundle
        openFragment(teamDetailFragment)

        favoriteTeamState()
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_detail_menu, menu)
        menuItem = menu
        setFavoritesTeam()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeTeamFavorites() else addTeamToFavorite()

                isFavorite = !isFavorite
                setFavoritesTeam()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addTeamToFavorite() {
        try {
            database.use {
                insert(FavoritesTeam.TABLE_FAVTEAM,
                        FavoritesTeam.TEAM_ID to team.teamID,
                        FavoritesTeam.TEAM_NAME to team.teamName,
                        FavoritesTeam.TEAM_BADGE to team.teamBadge,
                        FavoritesTeam.FORMED_YEAR to team.formedYear,
                        FavoritesTeam.STADIUM to team.stadium)
            }
            snackbar(activity_team_detail, "Added to favorites").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(activity_team_detail, e.localizedMessage).show()
        }
    }

    private fun removeTeamFavorites() {
        try {
            database.use {
                delete(FavoritesTeam.TABLE_FAVTEAM, "(TEAM_ID = {id})",
                        "id" to id)
            }
            snackbar(activity_team_detail, "Remove from favorites").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(activity_team_detail, e.localizedMessage).show()
        }
    }


    private fun setFavoritesTeam() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteTeamState() {
        database.use {
            val result = select(FavoritesTeam.TABLE_FAVTEAM).whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoritesTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
