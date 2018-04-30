package com.arsan.submissionapp.ui.matchespage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_match_page.*

import com.arsan.submissionapp.R
import com.arsan.submissionapp.R.id.match_view_pager
import com.arsan.submissionapp.ui.nextmatch.NextMatchFragment
import com.arsan.submissionapp.ui.prevmatch.PrevMatchFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchPageFragment : Fragment() {

    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_match_page, container, false)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.match_view_pager)
        viewPager.adapter = viewPagerAdapter

        return view
    }

    companion object {
        fun newInstance() : MatchPageFragment = MatchPageFragment()
    }

}

