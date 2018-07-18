package com.arsan.submissionapp.ui.teamdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.arsan.submissionapp.R
import kotlinx.android.synthetic.main.fragment_team_overview.*
import org.jetbrains.anko.bundleOf


/**
 * A simple [Fragment] subclass.
 *
 */
class TeamDetailFragment : Fragment() {

    private lateinit var viewPager: ViewPager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val idnya:String = arguments?.getString("data")?:"no data"
        val view: View = inflater.inflate(R.layout.fragment_team_detail, container, false)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, idnya)
        viewPager = view.findViewById(R.id.team_view_pager)
        viewPager.adapter = viewPagerAdapter

        //Toast.makeText(activity, arguments?.getString("data")?:"no data", Toast.LENGTH_LONG).show()

        return view
    }

    companion object {
        fun newInstance(): TeamDetailFragment = TeamDetailFragment()
    }

    private class ViewPagerAdapter(fragmentManager: FragmentManager, idTeam : String) : FragmentStatePagerAdapter(fragmentManager) {
        private var idBundle = idTeam
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putString("data", idBundle)
            if (position == 0) {
                var teamOverviewFragment = TeamOverviewFragment()
                teamOverviewFragment.arguments = bundle
                return teamOverviewFragment
            } else {
                val teamPlayersFragment = TeamPlayersFragment()
                teamPlayersFragment.arguments = bundle
                return teamPlayersFragment
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0) {
                return "Overview"
            } else {
                return "Player"
            }
        }

    }
}
