package com.arsan.submissionapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.arsan.submissionapp.R.menu.navigation
import com.arsan.submissionapp.ui.favorites.FavoritesMatchFragment
import com.arsan.submissionapp.ui.matchespage.MatchPageFragment
import com.arsan.submissionapp.ui.nextmatch.NextMatchFragment
import com.arsan.submissionapp.ui.prevmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                val matchPageFragment = MatchPageFragment.newInstance()
                openFragment(matchPageFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_prevmatch -> {
                val prevMatchFragment = PrevMatchFragment.newInstance()
                openFragment(prevMatchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_nextmatch -> {
                val nextMatchFragment = NextMatchFragment.newInstance()
                openFragment(nextMatchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoritesMatchFragment = FavoritesMatchFragment.newInstance()
                openFragment(favoritesMatchFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(PrevMatchFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
