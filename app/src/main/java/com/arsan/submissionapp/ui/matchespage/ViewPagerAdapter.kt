package com.arsan.submissionapp.ui.matchespage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.arsan.submissionapp.ui.nextmatch.NextMatchFragment
import com.arsan.submissionapp.ui.prevmatch.PrevMatchFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0){
            return NextMatchFragment.newInstance()
        }else{
            return PrevMatchFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position ==0){
            return "Next"
        }else{
            return "Past"
        }
    }

}
