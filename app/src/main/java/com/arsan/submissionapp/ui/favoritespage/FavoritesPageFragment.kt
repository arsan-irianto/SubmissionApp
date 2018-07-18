package com.arsan.submissionapp.ui.favoritespage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.fragment_match_page.*

import com.arsan.submissionapp.R
import com.arsan.submissionapp.R.id.match_view_pager
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.ui.nextmatch.NextMatchAdapter
import com.arsan.submissionapp.ui.nextmatch.NextMatchFragment
import com.arsan.submissionapp.ui.nextmatch.NextMatchPresenter
import com.arsan.submissionapp.ui.nextmatch.NextMatchView
import com.arsan.submissionapp.ui.prevmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.fragment_match_page.view.*
import org.jetbrains.anko.support.v4.toast
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesPageFragment : Fragment() {

    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_favorites_page, container, false)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.favorites_view_pager)
        viewPager.adapter = viewPagerAdapter

        return view
    }

    companion object {
        fun newInstance(): FavoritesPageFragment = FavoritesPageFragment()
    }

    private class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return FavoritesMatchFragment.newInstance()
            } else {
                return FavoritesTeamFragment.newInstance()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0) {
                return "Matches"
            } else {
                return "Teams"
            }
        }

    }

}

