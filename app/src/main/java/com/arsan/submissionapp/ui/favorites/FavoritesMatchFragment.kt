package com.arsan.submissionapp.ui.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arsan.submissionapp.R


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_match, container, false)
    }


    companion object {
        fun newInstance(): FavoritesMatchFragment = FavoritesMatchFragment()
    }
}
