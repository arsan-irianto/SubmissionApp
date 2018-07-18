package com.arsan.submissionapp.ui.matchespage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.*
import com.arsan.submissionapp.R
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

        setHasOptionsMenu(true)

        val view: View = inflater.inflate(R.layout.fragment_match_page, container, false)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.match_view_pager)
        viewPager.adapter = viewPagerAdapter

        return view
    }

    companion object {
        fun newInstance(): MatchPageFragment = MatchPageFragment()
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return NextMatchFragment.newInstance()
            } else {
                return PrevMatchFragment.newInstance()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0) {
                return "Next"
            } else {
                return "Past"
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.searchmenu, menu)
        //super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val searchMatchFragment = SearchMatchFragment()
        openFragment(searchMatchFragment)
/*        val intent = Intent(this, MatchSearchActivity::class.java)
        startActivity(intent)*/
        //startActivity<MatchSearchActivity>()
        return super.onOptionsItemSelected(item)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}

