package com.arsan.submissionapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.arsan.submissionapp.ui.favoritespage.FavoritesMatchFragment
import com.arsan.submissionapp.ui.favoritespage.FavoritesPageFragment
import com.arsan.submissionapp.ui.matchespage.MatchPageFragment
import com.arsan.submissionapp.ui.matchespage.MatchSearchActivity
import com.arsan.submissionapp.ui.teamspage.TeamsPageFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                val matchPageFragment = MatchPageFragment.newInstance()
                openFragment(matchPageFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                val teamsPageFragment = TeamsPageFragment.newInstance()
                openFragment(teamsPageFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoritesPageFragment = FavoritesPageFragment.newInstance()
                openFragment(favoritesPageFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(MatchPageFragment.newInstance())

        toolbar.title = "Football Match"
        setSupportActionBar(toolbar)

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
